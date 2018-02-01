package org.com.dev.controller;

import java.util.List;

import org.com.dev.entity.AjaxJson;
import org.com.dev.entity.Json;
import org.com.dev.entity.MachineGroup;
import org.com.dev.service.MachineGroupService;
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
@RequestMapping("/machineGroupController")
public class MachineGroupController {
	 private static final Logger logger = LoggerFactory.getLogger(MachineGroupController.class);
		
	@Autowired
	private MachineGroupService service;

	// saveRow
	@RequestMapping(value = "/saveRow")
	@ResponseBody
	public AjaxJson saveRow(int id, String name) {
		AjaxJson j = new AjaxJson();
		MachineGroup row;
		row = service.getByName(name);
		if (row == null) {
			if (id != 0) {
				row = service.get(id);
			} else {
				row = new MachineGroup();
			}
			row.setName(name);
			;
			service.save(row);

		} else {
			j.setSuccess(false);
			j.setMsg("名称已存在！");
		}
		return j;
	}

	
	// deleteRows
		@ResponseBody
		@RequestMapping(value = "/delRow", method = RequestMethod.POST)
		public String delRow(int id) {
		  		service.delete(id);
		 	return "0";
		}
	
	// deleteRows
	@ResponseBody
	@RequestMapping(value = "/delRows", method = RequestMethod.POST)
	public AjaxJson delRows(@RequestParam(value = "ids") String ids) {
		String[] idarry = ids.split(",");
		AjaxJson j = new AjaxJson();
		for (int i = 0; i < idarry.length; i++) {
			System.out.println("删除数据" + idarry[i]);
			service.delete(Integer.parseInt(idarry[i]));
		}
		j.setMsg("删除数据成功！");
		return j;
	}

	@ResponseBody
	@RequestMapping(value="/getAllRow", method = RequestMethod.POST)
	public String getRows() {
	 	return JSON.toJSONString(service.getAll());
	}
	// 查
	@ResponseBody
	@RequestMapping(value="/getRows", method = RequestMethod.POST)
	public String getRows(@RequestParam(value = "page", required = false) String pageNoStr,
			@RequestParam(value = "rows", required = false) String rows) {
		Json j = new Json();
		if(pageNoStr==null||rows==null){
			List<MachineGroup> list = service.getAll();
			j.setRows(service.getAll());
			j.setTotal((long)list.size());
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
			Page<MachineGroup> page = service.getPage(pageNo, rowsize);
	 		j.setTotal(page.getTotalElements());
			j.setRows(page.getContent());
		}

		return JSON.toJSONString(j);
	}

	// 校验
	@ResponseBody
	@RequestMapping(value = "validate", method = RequestMethod.POST)
	public String validateDesc(@RequestParam(value = "desc", required = true) String desc) {
		MachineGroup machineGroup = service.getByName(desc);
		if (machineGroup == null) {
			return "0";
		} else {
			return "1";
		}
	}
}
