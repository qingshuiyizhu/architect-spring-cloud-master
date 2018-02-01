package org.com.dev.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.com.dev.entity.AjaxJson;
import org.com.dev.entity.Button;
import org.com.dev.entity.Command;
import org.com.dev.service.ButtonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


@Controller
@RequestMapping("/buttonController")
public class ButtonController {
   private static final Logger logger = LoggerFactory.getLogger(ButtonController.class);

	@Autowired
	private ButtonService service;
	// 遍历所有按钮找到mid所对应的程序

	@RequestMapping(value = "/getOrderByMid")
	@ResponseBody
	public String getOrderByMid(int id) {

		List<Button> btns = service.findAll();
		List<Command> commands = new ArrayList<Command>();
		List<Command> contents;
		for (Button button : btns) {
			contents = new ArrayList<Command>();
			contents = JSONObject.parseArray(button.getContent(), Command.class);
			commands.addAll(contents);
		}
		Map<String, Command> result = new HashMap<String, Command>();

		StringBuffer str;
		for (Command command : commands) {
			if (id == command.getMid()) {
				str = new StringBuffer();
				str.append(command.getMid());
				str.append(command.getMid());
				str.append(command.getAppPath());
				result.put(str.toString(), command);
			}
		}

		// 遍历map取得该设备的指令
		contents = new ArrayList<Command>();
		for (Command v : result.values()) {
			contents.add(v);
		}
		return JSON.toJSONString(contents);
	}

	@RequestMapping(value = "/saveRow")
	@ResponseBody
	public AjaxJson saveRow(int id, String name, int tid) {
		// 判断name ==other row'name?
		AjaxJson j = new AjaxJson();
		Button row = service.getByName(name);
		// 数据已经存在
		if (row != null && (0 == id || row.getId() != id)) {
			j.setSuccess(false);
			j.setMsg("数据库已有相同数据！");
			return j;
		} else if (0 == id) {
			row = new Button();
		} else {
			row = service.get(id);
		}
		row.setName(name);
		row.setTid(tid);
		service.save(row);
		logger.info("保存按钮成功，按钮名称为："+name +"按钮类型id为:"+tid);
		return j;

	}

	@RequestMapping(value = "/delRow")
	@ResponseBody
	public AjaxJson delRow(int id) {
		AjaxJson j = new AjaxJson();
		service.delete(id);
		return j;
	}

	@RequestMapping(value = "/editRow")
	@ResponseBody
	public AjaxJson editRow(int id, String cont) {
		AjaxJson j = new AjaxJson();
		Button row = service.get(id);
		row.setContent(cont);
		service.save(row);
		logger.info("保存按钮的指令内容成功，按钮id为："+id+"内容为:"+cont);
		
		return j;
	}
	@RequestMapping(value = "/getRowContent")
	@ResponseBody
	public String  getRowContent(int id) {
		return service.get(id).getContent();	
	}
	
	@RequestMapping(value = "/findRows1")
	@ResponseBody
	public String  findRows1() {
		return JSON.toJSONString(service.findAll_1());
	}
}
