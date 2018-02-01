package org.com.dev.controller;

import java.util.Map;

import org.com.dev.entity.MachineType;
import org.com.dev.service.MachineTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MachineTypeController {
	 private static final Logger logger = LoggerFactory.getLogger(MachineTypeController.class);
		
	@Autowired
	private MachineTypeService machineTypeService;
 
	@RequestMapping(value="/machineType",method=RequestMethod.POST)
	public String save(MachineType machineType){
		machineTypeService.save(machineType);
		return "redirect:/machineTypes";
	}
	
	@RequestMapping(value="/machineType/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id){
		machineTypeService.delete(id);
		return "redirect:/machineTypes";
	}
  
	@RequestMapping(value="/machineType/{id}",method=RequestMethod.PUT)
	public String update(MachineType machineType){
		machineTypeService.save(machineType);
		return "redirect:/machineTypes";
	}
	
	@RequestMapping(value="/machineType/{id}", method=RequestMethod.GET)
	public String input(@PathVariable("id") Integer id, Map<String, Object> map){
		MachineType machineType = machineTypeService.get(id);
		map.put("machineType", machineType);
     	return "machineType/input";
	}
	 
	@RequestMapping(value="/machineType",method=RequestMethod.GET)
	public String input(Map<String,Object> map){
     	map.put("machineType", new MachineType());
		return "machineType/input";
	}
 
	@RequestMapping("/machineTypes")
	public String list(@RequestParam(value="pageNo", required=false, defaultValue="1") String pageNoStr, 
			Map<String, Object> map){
		int pageNo = 1;
		
		try {
			//对 pageNo 的校验
			pageNo = Integer.parseInt(pageNoStr);
			if(pageNo < 1){
				pageNo = 1;
			}
		} catch (Exception e) {}
		
		Page<MachineType> page = machineTypeService.getPage(pageNo, 5);
		map.put("page", page);
	 	return "machineType/list";
	}
	
	@ResponseBody
	@RequestMapping(value="machineType/validateDesc",method=RequestMethod.POST)
	public String validateDesc(@RequestParam(value="desc",required=true) String desc){
		MachineType machineType = machineTypeService.getByDesc(desc);
		if(machineType == null){
			return "0";
		}else{
			return "1";
		}
	}
}
