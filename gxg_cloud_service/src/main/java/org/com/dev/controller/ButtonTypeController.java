package org.com.dev.controller;

import java.util.List;

import org.com.dev.entity.AjaxJson;
import org.com.dev.entity.ButtonType;
import org.com.dev.entity.Json;
import org.com.dev.service.ButtonTypeService;
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
@RequestMapping("/buttonTypeController")
public class ButtonTypeController {
	 private static final Logger logger = LoggerFactory.getLogger(ButtonTypeController.class);
		
	@Autowired
	private ButtonTypeService service;
			// saveRow
	@RequestMapping(value = "/saveRow")
	@ResponseBody
	public AjaxJson saveRow(int id, String name) {
		AjaxJson j = new AjaxJson();
		ButtonType row;
		row = service.getByName(name);
		if (row == null) {
			if (id != 0) {
				row = service.get(id);
			} else {
				row = new ButtonType();
			}
			row.setName(name);
			service.save(row);
			logger.info("成功添加按钮类型名称:"+name);
		} else {
			j.setSuccess(false);
			j.setMsg("名称已存在！");
		}
		
		return j;
	}

	@ResponseBody
	@RequestMapping(value = "/getAllRow", method = RequestMethod.POST)
	public String getRows() {
		return JSON.toJSONString(service.getAll());
	}

	@ResponseBody
	@RequestMapping(value = "/getRows", method = RequestMethod.POST)
	public String getRows(@RequestParam(value = "page", required = false) String pageNoStr,
			@RequestParam(value = "rows", required = false) String rows) {
		Json j = new Json();
		if (pageNoStr == null || rows == null) {
			List<ButtonType> list = service.getAll();
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
			Page<ButtonType> page = service.getPage(pageNo, rowsize);
			j.setTotal(page.getTotalElements());
			j.setRows(page.getContent());
		}
		return JSON.toJSONString(j);
	}
}
