package org.com.dev.controller;

import java.util.List;

import org.com.dev.entity.AjaxJson;
import org.com.dev.entity.Json;
import org.com.dev.entity.SortButton;
import org.com.dev.service.SortButtonService;
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
@RequestMapping("/sortButtonController")
public class SortButtonController {
	 private static final Logger logger = LoggerFactory.getLogger(SortButtonController.class);
		
	@Autowired
	private SortButtonService service;

	@ResponseBody
	@RequestMapping(value = "/getRows", method = RequestMethod.POST)
	public String getRows(@RequestParam(value = "page", required = false, defaultValue = "1") String pageNoStr,
			@RequestParam(value = "rows", required = false, defaultValue = "10") String rows) {
		Json j = new Json();

		if (pageNoStr == null || rows == null) {
			List<SortButton> list = service.getAll();
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
			}
			Page<SortButton> page = service.getPage(pageNo, rowsize);
			j.setTotal(page.getTotalElements());
			j.setRows(page.getContent());
		}

		return JSON.toJSONString(j);
	}

	@ResponseBody
	@RequestMapping(value = "/delRow", method = RequestMethod.POST)
	public AjaxJson delRow(@RequestParam(value = "id") int id) {
		AjaxJson j = new AjaxJson();
		service.delete(id);
		return j;
	}

	@RequestMapping(value = "/saveRow")
	@ResponseBody
	public AjaxJson saveRow(int id, String name) {
		AjaxJson j = new AjaxJson();
		SortButton row;
		row = service.getByName(name);
		if (row == null) {
			if (id != 0) {
				row = service.get(id);
			} else {
				row = new SortButton();
			}
			row.setName(name);
			service.save(row);

		} else {
			j.setSuccess(false);
			j.setMsg("名称已存在！");
		}
		return j;
	}

	@RequestMapping(value = "/editRow")
	@ResponseBody
	public AjaxJson editRow(int id, String cont) {
		AjaxJson j = new AjaxJson();
		SortButton row;
		row = service.get(id);
		row.setContent(cont);
		service.save(row);
		return j;
	}

	@RequestMapping(value = "/getCont")
	@ResponseBody
	public String getCont(int id) {
		return service.get(id).getContent();
	}
}
