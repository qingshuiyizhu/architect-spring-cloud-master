package org.com.dev.controller;

import java.util.ArrayList;
import java.util.List;

import org.com.dev.entity.ClientFile;
import org.com.dev.entity.Machine;
import org.com.dev.service.ClientFileService;
import org.com.dev.service.MachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/clientFileController")
public class ClientFileController {
	 private static final Logger logger = LoggerFactory.getLogger(ClientFileController.class);

	@Autowired
	private MachineService machineService;
	@Autowired
	private ClientFileService clientFileService;
			// saveRow
	@RequestMapping(value = "/saveRows")
	@ResponseBody
	public String saveRows(String clientFiles) {
		System.out.println(clientFiles);
		List<ClientFile> lists = JSON.parseArray(clientFiles, ClientFile.class);
		for (ClientFile clientFile : lists) {
			clientFileService.save(clientFile);
		}
		return null;

	}

	@RequestMapping(value = "/getClientTree")
	@ResponseBody
	public String getClientTree() {

		List<ClientFile> clients = new ArrayList<ClientFile>();

		List<Machine> machines = machineService.getAll();
		for (Machine machine : machines) {
			clients.add(new ClientFile(machine.getId(),0,machine.getName()));
		}

		List<ClientFile> clientsList = clientFileService.findAll();
		for (ClientFile clientFile : clientsList) {
			if(clientFile.getIsdel()==0){
				clientFile.setId(clientFile.getId()+9999);
				clientFile.setPath(clientFile.getName());
				clientFile.setName(clientFile.getName()+"-同步完成："+clientFile.getSchedule()+"%");
	 			clients.add(clientFile);
				
			}
		}
		return JSON.toJSONString(clients);

	}
  	// delRow 更新isdel的值为1
		@RequestMapping(value = "/editRow")
		@ResponseBody
		public String editRow(Integer id) {
		 		clientFileService.editRow(id-9999);
		 		logger.info("在clientFile表中，将id为"+(id-9999)+"的数据删除！");
	 		return null;

		}

}
