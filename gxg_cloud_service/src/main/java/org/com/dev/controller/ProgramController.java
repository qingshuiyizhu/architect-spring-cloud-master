package org.com.dev.controller;

import java.util.List;

import org.com.dev.entity.AjaxJson;
import org.com.dev.entity.Json;
import org.com.dev.entity.Program;
import org.com.dev.service.ProgramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/programController")
public class ProgramController {
	 private static final Logger logger = LoggerFactory.getLogger(ProgramController.class);
		
	@Autowired
	private ProgramService service;
	 
	// 修改程序
	@RequestMapping(value = "/editRow")
	@ResponseBody
	public AjaxJson editRow(int id, String name, String appPath) {
		AjaxJson j = new AjaxJson();
		Program row = null;
		if(0==id){
			row = new Program();
	 	}else{
	 		row = service.get(id);
	 	}
	   	if (null != row) {
			row.setName(name);
			row.setAppPath(appPath);
			service.save(row);
		} else {
			j.setSuccess(false);
		
		}
		return j;

	}
	
 
	@ResponseBody
	@RequestMapping(value = "/getRows", method = RequestMethod.POST)
	public Json getRows(@RequestParam(value = "page", required = false, defaultValue = "1") String pageNoStr,
			@RequestParam(value = "rows", required = false, defaultValue = "10") String rows) {
		Json j = new Json();
		if (pageNoStr == null || rows == null) {
			List<Program> list = service.findAll();
			j.setRows(list);
			j.setTotal((long) list.size());
		}  else {
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
			Page<Program> page = service.getPage(pageNo, rowsize);
			j.setTotal(page.getTotalElements());
			j.setRows(page.getContent());
		}
		return j;

	}
	
	@RequestMapping(value = "/delRow")
	@ResponseBody
	public AjaxJson delRow(int id) {
		AjaxJson j = new AjaxJson();
		service.delete(id);
		return j;
	}
	 
}
