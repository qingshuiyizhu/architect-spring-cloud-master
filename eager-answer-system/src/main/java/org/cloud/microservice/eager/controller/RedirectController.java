package org.cloud.microservice.eager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class RedirectController {
/*	public  static List<String> names = new ArrayList<String>();
	 	static {
			System.out.println("日志信息：" + "初始化 ExamWebSocketHandler");
			if (names.size() < 1) {
				names.add("学员1");
				names.add("学员2");
				names.add("学员3");
				names.add("学员4");
				names.add("学员5");
				names.add("学员6");
				names.add("学员7");
				names.add("学员8");
				names.add("学员9");
				names.add("学员10");
				System.out.println("日志信息：" + "初始化学员姓名");
			}
		}

	//重定页面
	@RequestMapping(value="/student",method=RequestMethod.GET) 
	public String Student(HttpServletRequest request){
	 	if ( names.size() > 0) {
			request.getSession().setAttribute("username",names.get(0));
			 names.remove(0);
			return "student";
		}else {
				return "error";
			}
	}	
	 */ 
	//重定页面
	@RequestMapping(value="/master",method=RequestMethod.GET) 
	public String programPage(HttpServletRequest request,Model model){
		
	   request.getSession().setAttribute("username","master");
	   model.addAttribute("username", "masterModel");
	   System.out.println("master login ");
	 	return "master";
    }	
 }
