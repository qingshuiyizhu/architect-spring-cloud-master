package org.com.dev.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RedirectController {
	 private static final Logger logger = LoggerFactory.getLogger(RedirectController.class);
		
	@RequestMapping(value = "btns/{id}", method = RequestMethod.GET)
	public String btns(@PathVariable("id")int id,HttpServletRequest request) {
	 	request.getSession().setAttribute("machineid",id);
	 	return "redirect:/client/btns";
	 
	}
	@RequestMapping(value = "/manager/{page}", method = RequestMethod.GET)
	 public String manager(@PathVariable("page") String page) {
		System.out.println("-------------------denglumanager--------------------------------------------");
		System.out.println("manager/" + page);
		return "manager/" + page;
	}
	@RequestMapping(value = "/client/{page}", method = RequestMethod.GET)
	public String client(@PathVariable("page") String page) {
		System.out.println("-------------------dengluclient--------------------------------------------");
		
		return "client/" + page;
	}
}
