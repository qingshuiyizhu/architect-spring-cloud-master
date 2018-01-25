package org.cloud.mircoservice.log.controller;

import org.cloud.mircoservice.log.entity.SysLog;
import org.cloud.mircoservice.log.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;


@Controller
public class SysLogController {
 	@Autowired
	 private SysLogService sysLogService; 
	 //@GetMapping("\test")
	@RequestMapping(value = "/w", method = RequestMethod.GET)
    @ResponseBody  
	public String test(){
		System.out.println("ffffffffffffffffff");
		SysLog sysLog = new SysLog();
		sysLog.setMethodName("fffffffffffff");
		sysLog.setModuleName("dddddddd");
	 	sysLogService.save(sysLog);
		return "success";		
	}
	 
	@RequestMapping(value = "/w1", method = RequestMethod.GET)
    @ResponseBody  
	public String test1(){
		System.out.println("ffffffffffffffffff");
		SysLog sysLog = new SysLog();
		sysLog.setMethodName("fffffffffffff");
		sysLog.setModuleName("dddddddd");
	 	sysLogService.save(sysLog);
	 	sysLogService.findAll();
		return 	JSON.toJSONString(sysLogService.findAll());		
	}
}
