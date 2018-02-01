package org.com.dev.controller;

import java.util.List;

import org.com.dev.entity.AjaxJson;
import org.com.dev.entity.ButtonType;
import org.com.dev.entity.Json;
import org.com.dev.entity.JsonMsg;
import org.com.dev.entity.UserRole;
import org.com.dev.service.UserRoleService;
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

@Controller
@RequestMapping("/userRoleController")
public class UserRoleController {
	 private static final Logger logger = LoggerFactory.getLogger(UserRoleController.class);
		
	@Autowired
	private UserRoleService userRoleService;
 
	
	@ResponseBody
	@RequestMapping(value = "/getAllRow", method = RequestMethod.POST)
	public String getRows() {
		return JSON.toJSONString(userRoleService.getAll());
	}

	@ResponseBody
	@RequestMapping(value ="/getRows"/*,method = RequestMethod.POST*/)
	public String getRows(@RequestParam(value = "page", required = false) String pageNoStr,
			@RequestParam(value = "rows", required = false) String rows) {
		Json j = new Json();
		if (pageNoStr == null || rows == null) {
			List<UserRole> list = userRoleService.getAll();
			j.setRows(list);
			j.setTotal((long) list.size());
		} else {

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
				e.printStackTrace();
			}
			Page<UserRole> page = userRoleService.getPage(pageNo, rowsize);
			j.setTotal(page.getTotalElements());
			j.setRows(page.getContent());
		}
		return JSON.toJSONString(j);
	}
 

	
	@RequestMapping(value="/delRow")
	@ResponseBody
	public JsonMsg delRow(String[] ids) {

	 	return null;
	}

	@RequestMapping(value="/saveRow")
	@ResponseBody
	public AjaxJson saveRow(int id, String name, String appPath) {
		AjaxJson j = new AjaxJson();
		UserRole row;
		row = userRoleService.getByName(name);
	  	if (id != 0) {
				row = userRoleService.get(id);
			} else {
				row = new UserRole();
			}
			row.setName(name);
			userRoleService.save(row);
 	return j;
	}

}
