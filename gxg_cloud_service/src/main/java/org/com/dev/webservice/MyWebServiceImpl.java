package org.com.dev.webservice;

import java.util.List;

import javax.jws.WebService;

import org.com.dev.entity.ClientFile;
import org.com.dev.entity.Machine;
import org.com.dev.service.ClientFileService;
import org.com.dev.service.MachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@WebService(serviceName = "cxfWebService",// 与接口中指定的name一致
		targetNamespace = "http://webservice.dev.com.org/", // 与接口中的命名空间一致,一般是接口的包名倒
		endpointInterface = "org.com.dev.webservice.MyWebService"// 接口地址
)
@Component
public class MyWebServiceImpl implements MyWebService {
	@Autowired
	private MachineService machineService;
	@Autowired
	private ClientFileService clientFileService;
     private static final Logger logger = LoggerFactory.getLogger(MyWebServiceImpl.class);
	//得到同步文件列表
	@Override
	public String getFiles(String mac) {
		logger.info("MAC地址为"+mac+"的客户端请求同步文件数据");
	 	Machine machine = null;
	 	machine = machineService.getByMAC(mac);
	 	if(machine == null){
	 		return "尚未在中控端注册设备";
	 	}
	  List<ClientFile> cf = clientFileService.findByCidDown(machine.getId());
			logger.info("MAC地址为"+mac+"的客户端请求同步文件数据的返回值为："+JSON.toJSONString(cf));
		 	return JSON.toJSONString(cf);   
	  
	}

	//删除文件 	文件路径
	@Override
	public String delFile(String mac, String path) {
		Machine machine = machineService.getByMAC(mac);
		clientFileService.delFile(machine.getId(),path);
  		
		return "0";
	}

	//文件同步完成率 
	@Override
	public String downFile(String mac, String remotePath,String fileName, Integer finish) {
		Machine machine = machineService.getByMAC(mac);
		clientFileService.reFile(machine.getId(),remotePath,fileName,finish);
		return "0";
	}
	 
	
	/*
    //指令转发（对多个转发）
	@Override
	public String transmit(String macs, String appPath,String porgram) {
	    String macList[] = 	macs.split(",");
		for (String mac : macList) {
		 	SocketData data= new SocketData();
		 	data.setOrderType(2);
			data.setAppPath(appPath);
			data.setMac(mac);
			data.setPorgram(porgram);
			 
 	}
	 	return "0";
	}
	//文件转发(上传文件进行转发)
	@Override
	public String transfile(String mac, byte[] b, int len,String fileName) {
		//先把这个文件存入ftp，插入数据库，给客户端下达同步指令进行文件同步
	    InputStream inputStream = new ByteArrayInputStream(b);   
	 	ftp.ftpUtils().openFTP();
		boolean sign = ftp.ftpUtils().uploadFile("/转发文件/", fileName, inputStream);
	 	ftp.ftpUtils().closeFTP();
	    SocketData data= new SocketData();
		data.setOrderType(11);
	 	data.setMac(mac);
		data.setPorgram("/转发文件/"+fileName);
	  	return "0";
	}
	
	*/
}
