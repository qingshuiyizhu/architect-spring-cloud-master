package org.com.dev.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.com.dev.entity.AjaxJson;
import org.com.dev.entity.Json;
import org.com.dev.entity.User;
import org.com.dev.realm.CryptographyUtil;
//import org.com.dev.realm.CryptographyUtil;
import org.com.dev.service.UserRoleService;
import org.com.dev.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


@Controller
@RequestMapping("/userController") 
public class UserController {
	 private static final Logger logger = LoggerFactory.getLogger(UserController.class);
		
	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;
  	@RequestMapping(value="/manager/login",method = RequestMethod.POST)
	public String managerLogin(String name ,String password,HttpServletRequest request) {
  		
  		User user = userService.getByName(name);
  		if(user !=null && user.getPassword().equals(CryptographyUtil.md5(password))){
  			return "redirect:/manager/main";	
  		}else{
  			return "/manager/login";
  		}
   		 
  		/*Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(user.getName(), user.getPassword());
			try{
			subject.login(token);
			Session session = subject.getSession();
			System.out.println("sessionId:"+session.getId());
			System.out.println("sessionHost:"+session.getHost());
			System.out.println("sessionTimeout:"+session.getTimeout());
			session.setAttribute("info", "session的数据");
			return "redirect:/manager/index";
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("user", user);
			request.setAttribute("errorMsg", "用户名或密码错误！");
			return "/manager/login";
		}*/
  	}
	
	 @RequestMapping(value="/client/login",method = RequestMethod.POST)
	public String clientLogin(String username ,String password,HttpServletRequest request) {
	
		 System.out.println("客户端登陆，username="+username+"，password="+password);
		 
		 
		 User user = userService.getByName(username);
	  		if(user !=null && user.getPassword().equals(CryptographyUtil.md5(password))){
	  			
	  			return "redirect:/client/index";	
	  		}else{
	  			return "/client/login";
	  		}
		 
		 	 
		// Subject subject=SecurityUtils.getSubject();
	/*	UsernamePasswordToken token=new UsernamePasswordToken(user.getName(), CryptographyUtil.md5(user.getPassword()));
		try{
			subject.login(token);
			Session session=subject.getSession();
			System.out.println("sessionId:"+session.getId());
			System.out.println("sessionHost:"+session.getHost());
			System.out.println("sessionTimeout:"+session.getTimeout());
			session.setAttribute("info", "session的数据");
			return "redirect:/client/index";
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("user", user);
			request.setAttribute("errorMsg", "用户名或密码错误！");
			return "/client/login";
		}*/
		//return "redirect:/client/index";
  	} 
	  
	@ResponseBody 
	@RequestMapping(value ="/getRows",method = RequestMethod.POST)
	public String getRows(@RequestParam(value = "page", required = false) String pageNoStr,
			@RequestParam(value = "rows", required = false) String rows) {
	  	int pageNo = 1;
		int rowsize = 10;
		try {
			// 对 pageNo 的校验
			pageNo = Integer.parseInt(pageNoStr);
			rowsize = Integer.parseInt(rows);
			if (pageNo < 1) {
				pageNo = 1;
			}
		} catch (Exception e) {
		}
		Page<User> page = userService.getPage(pageNo, rowsize);
		List<User> users = new ArrayList<User>();
	 	for (User user : page.getContent()) {
	  	 user.setRname(userRoleService.get(user.getRid()).getName());
			users.add(user); 
		} 
		System.out.println(JSONObject.toJSONString(page));
		Json j = new Json();
		j.setTotal(page.getTotalElements());
		j.setRows(users);
		return JSON.toJSONString(j);
	}

	
	@ResponseBody 
	@RequestMapping(value="/delRows",method = RequestMethod.POST)
	public AjaxJson delRow(@RequestParam(value = "ids") String ids) {
		String[] idarry = ids.split(",");
	 	AjaxJson j = new AjaxJson();
	  	 for (int i = 0; i < idarry.length; i++) {
	 		System.out.println("删除数据"+idarry[i]);
	 		userService.delete(Integer.parseInt(idarry[i]));
			} 
		 	j.setMsg("删除数据成功！");
			return j;	 
	}

	@RequestMapping(value="/saveRow")
	@ResponseBody
	public AjaxJson saveRow(int id, String name, String pwd, int role ) {
		AjaxJson j = new AjaxJson();
		User row;
		 
			if (id != 0) {
				row = userService.get(id);
			} else {
				row = new User();
			}
	 		row.setName(name);
			row.setPassword(CryptographyUtil.md5(pwd));
	   	    row.setRid(role);
			userService.save(row);
	 	return j;
	}

}
