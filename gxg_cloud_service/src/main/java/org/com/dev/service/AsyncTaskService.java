package org.com.dev.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.com.dev.controller.ButtonController;
import org.com.dev.entity.FileTransfer;
import org.com.dev.entity.Machine;
import org.com.dev.entity.SocketData;
import org.com.dev.util.SocketUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncTaskService {
	private static final Logger logger = LoggerFactory.getLogger(ButtonController.class);
	public static DatagramSocket DS = null;
	@Autowired
	private MachineService machineService;
 
	@Async
	public void ListenerClient(){
		// 数组长度最大为64K,1024为1K
				byte b[] = new byte[1024];
				DatagramPacket dp = null;
				try {
					DS = new DatagramSocket(5000);// 在此端口上进行监听;
					while (true) {
						// 创建一个数据报对象
						dp = new DatagramPacket(b, b.length);
						// 创建UDP协议的Socket对象
		 				System.out.println("接受端正在等待接收数据。。。");
						DS.receive(dp);// 接收数据，该方法会造成阻塞
						/*String data = new String(dp.getData(), 0, dp.getLength());
				        logger.info("中控端接收到心跳数据："+data);
						*/
						System.out.println("数据长度为：" + dp.getData().length);
					    executeAsyncTask(dp.getData(), dp);
				 		
					}
				} catch (Exception e) {
					if (DS != null) {
						DS.close();
					}
					 logger.error("中控端接收到心跳数据的SocketThread服务异常！");
					System.out.println("SocketThread服务出错");
					e.printStackTrace();
				}
	}
	
	 
	@Async
	public void executeAsyncTask(byte[] data, DatagramPacket dp) {
		try {
			// 从byte数组中还原SocketData对象
			ByteArrayInputStream bin = new ByteArrayInputStream(data);
			ObjectInputStream ois = new ObjectInputStream(bin);
			SocketData socketData = (SocketData) ois.readObject();
			SocketUtils.closeStream(ois);
			SocketUtils.closeStream(bin);

			System.out.println("接收端接收到数据：" + socketData);
			logger.info("接收端接收到数据：" + socketData);
			Machine machine = machineService.getByMAC(socketData.getMac());
			if (null == machine) {
				machine = new Machine();
				machine.setState(0);
				machine.setName(socketData.getHostName());
				machine.setMac(socketData.getMac());
				machine.setMgid(0);
				machine.setIp(dp.getAddress().getHostAddress());
				machine.setPort(dp.getPort());
			}
			machine.setHeartbeat(1);
			machineService.save(machine);

			// 转发文件或者指令 -1为心跳监测 其他指令转发 10为指令转发 	// 11为文件接收
			if (socketData.getOrderType() != -1) {
				transmit(socketData.getFileTransfer(), socketData.getOrderType(), machine.getIp(), machine.getPort());
			}

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}

	}

	// 转发
	private void transmit(FileTransfer fileTransfer, Integer orderType, String ip, int port) {
		System.out.println("进行指令或者文件转发：11位文件转发，12位指令转发，转发类型为:" + orderType);
		logger.info("进行指令或者文件转发：11位文件转发，12位指令转发，转发类型为:" + orderType);
		// 找到转发设备
		String[] macs = fileTransfer.getMacs();
    	SocketData socketData = new SocketData();
		socketData.setOrderType(orderType);
		socketData.setFileTransfer(fileTransfer);

		for (String mac : macs) {
			socketData.setMac(mac);
			try {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(bos);
				oos.writeObject(socketData);
				// 得到SocketData对象的byte数组
				byte[] ByteArray = bos.toByteArray();
				System.out.println("转发内容为：" + socketData + "转化为byte数组大小为：" + ByteArray.length);
				logger.info("转发内容为：" + socketData + "转化为byte数组大小为：" + ByteArray.length);
				SocketUtils.closeStream(oos);
				SocketUtils.closeStream(bos);
				SocketUtils.sendData(ByteArray, ip, port);
			} catch (Exception e) {
				System.out.println("转发文件出错，转发内容为：" + socketData);
				logger.error("转发文件出错，转发内容为：" + socketData);
				System.out.println(e);
				logger.error("错误原因为：" + e);
			}

		}

	}

}