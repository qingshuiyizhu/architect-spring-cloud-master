package org.cloud.microservice.eager.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/home")
	public String home(ModelMap modelMap) {
		modelMap.put("name", "Magical Sam");

		List<String> list = new ArrayList<>();
		list.add("sam a");
		list.add("sam b");
		list.add("sam c");
		list.add("sam d");
		modelMap.put("list", list);

		return "home";
	}

}
