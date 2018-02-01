package org.com.dev.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.com.dev.entity.Button;
import org.com.dev.entity.Command;
import org.com.dev.entity.Json;
import org.com.dev.entity.JsonMsg;
import org.com.dev.entity.Machine;
import org.com.dev.entity.Program;
import org.com.dev.entity.SocketData;
import org.com.dev.service.ButtonService;
import org.com.dev.service.FTPService;
import org.com.dev.service.MachineGroupService;
import org.com.dev.service.MachineService;
import org.com.dev.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping("/dwrController")
public class DwrController {
/*
 */
	@Autowired
	private ButtonService buttonService;
	@Autowired
	private MachineService machineService;
	@Autowired
	private MachineGroupService machineGroupService;
	@Autowired
	private ProgramService programService;
	private JsonMsg message;
	@Autowired
	private FTPService ftpservice;
	
	@RequestMapping(value = "/getFileTozTree" )
  	public String getFileTozTree() {
		return  ftpservice.getFileTozTree();
	}
	@RequestMapping(value = "/zTreeOnRemove",method=RequestMethod.POST)
	public String zTreeOnRemove(String path) {
		System.out.println("---------zTreeOnRemove------------"+path);
		boolean sign = ftpservice.zTreeOnRemove(path);
		if (sign) {
			return "删除成功！";
		} else {
			return "删除失败！";
		}

	}
	
public void writeToLocal(String destination, InputStream input)  
	        throws IOException {  
	    int index;  
	    byte[] bytes = new byte[1024];  
	    FileOutputStream downloadFile = new FileOutputStream(destination);  
	    while ((index = input.read(bytes)) != -1) {  
	        downloadFile.write(bytes, 0, index);  
	        downloadFile.flush();  
	    }  
	    downloadFile.close();  
	    input.close();  
	}  
	@RequestMapping(value = "/uploadFile" ,method=RequestMethod.POST)
	public String  uploadFile(@RequestParam("files") MultipartFile[] files, String path) {
	System.out.println("-----------------path= "+path);
		for(int i=0;i<files.length;i++){
			if(files !=null){
				try {
					
		 			String fileName =  files[i].getOriginalFilename();
				    InputStream inputStream = files[i].getInputStream();
				//   writeToLocal("F:\\"+fileName,inputStream);  
		 		    ftpservice.uploadFile(inputStream, fileName, path);
				  //  uploadFile("127.0.0.1", 21, "admin", "admin", path,fileName,inputStream);
		 		} catch (Exception e) {
		 			return "1";
		 		}
				
			}
		}
	 	 return "0";
	}
	
	
	  
	 /**
	 * 上传文件（可供Action/Controller层使用）
	 * @param hostname FTP服务器地址
	 * @param port FTP服务器端口号
	 * @param username FTP登录帐号
	 * @param password FTP登录密码
	 * @param pathname FTP服务器保存目录
	 * @param fileName 上传到FTP服务器后的文件名称
	 * @param inputStream 输入文件流
	 * @return
	 */
	 public  boolean uploadFile(String hostname, int port, String username, String password, String pathname, String fileName, InputStream inputStream){
	 boolean flag = false;
	 FTPClient ftpClient = new FTPClient();
	 ftpClient.setControlEncoding("UTF-8");
	 try {
	 //连接FTP服务器
	 ftpClient.connect(hostname, port);
	 //登录FTP服务器
	 ftpClient.login(username, password);
	 //是否成功登录FTP服务器
	 int replyCode = ftpClient.getReplyCode();
	 if(!FTPReply.isPositiveCompletion(replyCode)){
	 return flag;
	 }
	  
	 ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
	 ftpClient.makeDirectory(pathname);
	 ftpClient.changeWorkingDirectory(pathname);
	 ftpClient.storeFile(fileName, inputStream);
	 inputStream.close();
	 ftpClient.logout();
	 flag = true;
	 } catch (Exception e) {
	 e.printStackTrace();
	 } finally{
	 if(ftpClient.isConnected()){
	 try {
	 ftpClient.disconnect();
	 } catch (IOException e) {
	 e.printStackTrace();
	 }
	 }
	 }
	 return flag;
	 }
	
	
	
	
    /*@RequestMapping(value = "/uploadFile")
	public String uploadFile(InputStream inputStream, String fileName, String path) {
		ftpservice.uploadFile(inputStream, fileName, path);
		return null;
	}*/
	@RequestMapping(value = "/creatDir")
	public String creatDir(String path, String name) {
		boolean sign = ftpservice.creatDir(path, name);

		return "0";

	}
 
	public void sendData(SocketData data) {
  	Machine machine = machineService.getByMAC(data.getMac());
		String info = JSONObject.toJSONString(data);
		System.out.println("向客户端发送指令：" + info);
		try {
			byte[] bytes = info.getBytes();
			DatagramPacket dp = new DatagramPacket(bytes, 0, bytes.length, InetAddress.getByName(machine.getIp()),
					machine.getPort());
		 
			//Listener.DS.send(dp);// 发送完毕
			System.out.println("向客户端发送指令成功");
		} catch (Exception e) {
			System.out.println("向客户端发送指令：" + info + "失败");
			e.printStackTrace();
		}

	}
	@RequestMapping(value = "/issue" )
	public String issue(int id) {
		System.out.print("发送指令成功!" + id);
		// 根据按钮id查找记录
		Button button = buttonService.get(id);
		List<Command> contents = new ArrayList<Command>();
		try {
			contents = JSONObject.parseArray(button.getContent(), Command.class);

		 
			for (int i = 0; i < contents.size(); i++) {
				// udp发送数据
				Command content = contents.get(i);
				Machine machine = machineService.get(content.getMid());
				Program program = programService.get(content.getPid());
        		SocketData data = new SocketData();
				data.setAppPath(content.getAppPath());
				data.setMac(machine.getMac());
				data.setOrderType(2);
				data.setPorgram(program.getAppPath());
				sendData(data);
				System.out.println("发送指令成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "此按钮没有指令数据！";
		}

		return "0";
	}
	@RequestMapping(value = "/systemOrder" )
	public String systemOrder(int id, String order) {
		boolean sign = false;
		Machine machine = machineService.get(id);
		SocketData data = new SocketData();
		data.setPorgram(order);
		data.setOrderType(0);
		data.setMac(machine.getMac());
		sendData(data);
		System.out.println("发送指令成功!");
		sign = true;
		if (sign) {
			return "0";
		}
		return "1";
	}

	// -----------------------------------------------
	@RequestMapping(value = "/getButtons", method=RequestMethod.POST)
	// 获得所有按钮
	public String getButtons() {
		List<Button> buttons = buttonService.findAll_1();
		Json j = new Json();
		j.setRows(buttons);
		j.setTotal((long)buttons.size());
		return  JSON.toJSONString(j);
	}
	@RequestMapping(value = "/delMachineGroup", method=RequestMethod.POST)
	public String delMachineGroup(int mgid) {
		machineService.editMachine(mgid);
		machineGroupService.delete(mgid);
		return "0";
	}

	// 审核设备
	@RequestMapping(value = "/save_verify", method=RequestMethod.POST)
	public String save_verify(int id, int state) {
		message = new JsonMsg();
		Machine machine = machineService.get(id);
		if (machine.getState() != state) {
			System.out.println("更新状态！");
			machine.setState(state);
			machineService.save(machine);
		} else {
			message.setSuccess(false);
		}
		return JSON.toJSONString(message) ;

	}

}
