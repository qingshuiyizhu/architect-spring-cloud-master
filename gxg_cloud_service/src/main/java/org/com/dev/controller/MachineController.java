package org.com.dev.controller;

import java.util.List;

import org.com.dev.entity.AjaxJson;
import org.com.dev.entity.Json;
import org.com.dev.entity.Machine;
import org.com.dev.service.MachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

@RestController
@RequestMapping("/machineController")
public class MachineController {
	 private static final Logger logger = LoggerFactory.getLogger(MachineController.class);
		
	@Autowired
	private MachineService service;

	// 修改设备组与设备名称
	@RequestMapping(value = "/saveRow")
	public AjaxJson saveRow(int id, String name, String mgid) {
		
		System.out.println("id="+id+"name="+name+"mgid="+mgid);
		
		// 判断name ==other row'name?
		AjaxJson j = new AjaxJson();
		Machine row = service.getByName(name);
		// 数据已经存在
		if (row != null && (0 == id || row.getId() != id)) {
			j.setSuccess(false);
			j.setMsg("数据库已有相同数据！");
			return j;
		} else if (0 == id) {
			row = new Machine();
		} else {
			row = service.get(id);
		}
		row.setName(name);
		
		row.setMgid(Integer.parseInt(mgid));
		service.save(row);
		return j;

	}

	@RequestMapping(value = "/delRows", method = RequestMethod.POST)
	public AjaxJson delRow(@RequestParam(value = "ids") String ids) {
		String[] idarry = ids.split(",");
		AjaxJson j = new AjaxJson();
		for (int i = 0; i < idarry.length; i++) {
			service.delete(Integer.parseInt(idarry[i]));
		}
		j.setMsg("删除数据成功！");
		return j;
	}

	@RequestMapping(value = "/getRows", method = RequestMethod.POST)
	public String getRows(@RequestParam(value = "page", required = false) String pageNoStr,
			@RequestParam(value = "rows", required = false) String rows) {
		Json j = new Json();
		if (pageNoStr == null || rows == null) {
			List<Machine> list = service.getAll();
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
			Page<Machine> page = service.getPage(pageNo, rowsize);
			j.setTotal(page.getTotalElements());
			j.setRows(page.getContent());
		}
		return JSON.toJSONString(j);

	}

	// 显示通过审核的设备
	@RequestMapping(value = "/getRowsisv", method = RequestMethod.POST)
	public Json getRowsisv() {
		Json j = new Json();
		j.setRows(service.getIsPass());
		return j;

	}
	@RequestMapping(value = "/getRows1", method = RequestMethod.POST)
	public List<Machine> getRows1() {
	   	return service.getRows1();

	} 
	
	//根据设备组查找设备
	@RequestMapping(value = "/getMachineBymgid", method = RequestMethod.POST)
	public String getMachineBymgid(int mgid){
		System.out.println("------------------------------mgid="+mgid);
		System.out.println(JSON.toJSONString(service.getRows2(mgid)));
	   	return JSON.toJSONString(service.getRows2(mgid));
 	}
	
	//根据设备组查找设备
		@RequestMapping(value = "/getRows3", method = RequestMethod.POST)
		public List<Machine>getRows3(){
		   	return service.getRows3();
	 	}
}
