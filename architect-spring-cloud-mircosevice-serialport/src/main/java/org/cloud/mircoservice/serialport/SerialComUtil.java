package org.cloud.mircoservice.serialport;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.ResourceBundle;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;

public class SerialComUtil {
	// 计算机连接8路串口分配器的usb端口
	private String comUsb;
	// 给8路串口分配器发送指令的波特率
	private String uart8BaudRate;
	// 8串口分配器的起始指令
	private String startPart;
	// 8串口分配器的校验码
	private String checkPart;
	// 8串口分配器的分配的通道
	private String connector;
	// 投影仪的波特率映射值
	private String projectorBaudRate;
	// 封装数据的长度
	private String dataLength;
	// 投影机型号
	// 投影机连接串口分配器的通道 connector1, connector2,...
	// 发送给投影机的命令 open close
	public int excuteSingleProjector(String model, String port, String action, String baudRate ) {
		ResourceBundle uart8 = ResourceBundle.getBundle("uart8");
		this.comUsb = uart8.getString("com.usbport");

		HashSet<CommPortIdentifier> portSet = getAvailableSerialPorts();
		for (CommPortIdentifier comm : portSet) {
			System.out.println("可用串口：" + comm.getName() + " - " + getPortTypeName(comm.getPortType()));
			if (comUsb.equals(comm.getName())) {
        		this.uart8BaudRate = uart8.getString("usbport.baudrate");
				this.startPart = uart8.getString("usbport.start");
				this.checkPart =  uart8.getString(uart8.getString("check"));
			 	this.connector = uart8.getString("uart8." + port.trim());
				this.projectorBaudRate = uart8.getString("uart8."+baudRate.trim());
				ResourceBundle projector = ResourceBundle.getBundle("projector");
				// 发送给投影机的命令
				String projectorCmd = "";
				if (model.equals("") || model == null) {
					projectorCmd = projector.getString("common." + action);
				} else {
					projectorCmd = projector.getString(model + "." + action);
				}
				//控制导轨式8通道开关
			 	if(port.trim().equals("connector6")){
			 	 	//开电脑主机
			 		if(action.trim().equals("open")){
			 			projectorCmd = projector.getString("common.open");
			 			  // 数据长度
						dataLength = decimalToHex(projectorCmd.length() / 2);
			 		 	// 封装命令
						String finalCmd = startPart + connector + checkPart + projectorBaudRate + dataLength + projectorCmd;
						SerialPortRunnable serialPortRunnable = new SerialPortRunnable(uart8BaudRate, comUsb, finalCmd);
						serialPortRunnable.run();
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
						 	e.printStackTrace();
						}
						projectorCmd = projector.getString("common.close");
			 			  // 数据长度
						dataLength = decimalToHex(projectorCmd.length() / 2);
			 		 	// 封装命令
						 finalCmd = startPart + connector + checkPart + projectorBaudRate + dataLength + projectorCmd;
						 serialPortRunnable = new SerialPortRunnable(uart8BaudRate, comUsb, finalCmd);
						 serialPortRunnable.run();
				  		}else if(action.trim().equals("close")){
				  			projectorCmd = projector.getString("common.open");
				 			  // 数据长度
							dataLength = decimalToHex(projectorCmd.length() / 2);
				 		 	// 封装命令
							String finalCmd = startPart + connector + checkPart + projectorBaudRate + dataLength + projectorCmd;
							SerialPortRunnable serialPortRunnable = new SerialPortRunnable(uart8BaudRate, comUsb, finalCmd);
							serialPortRunnable.run();
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
							 	e.printStackTrace();
							}
							projectorCmd = projector.getString("common.close");
				 			  // 数据长度
							dataLength = decimalToHex(projectorCmd.length() / 2);
				 		 	// 封装命令
							 finalCmd = startPart + connector + checkPart + projectorBaudRate + dataLength + projectorCmd;
							 serialPortRunnable = new SerialPortRunnable(uart8BaudRate, comUsb, finalCmd);
							 serialPortRunnable.run();
			 	  		}
			 		} 
			   // 数据长度
			   dataLength = decimalToHex(projectorCmd.length() / 2);
			   // 封装命令
				String finalCmd = startPart + connector + checkPart + projectorBaudRate + dataLength + projectorCmd;
				SerialPortRunnable serialPortRunnable = new SerialPortRunnable(uart8BaudRate, comUsb, finalCmd);
				serialPortRunnable.run();
				//不能开线程
				/*Thread t = new Thread(serialPortRunnable);
				t.start();*/
                return 0;
			}
		}
		return 9999;
	}

 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 列出所有可用串口
	public static void listSerialPorts() {
		HashSet<CommPortIdentifier> portSet = getAvailableSerialPorts();
		for (CommPortIdentifier comm : portSet) {
			 System.out.println(comm.getName() + " - " + getPortTypeName(comm.getPortType()));
		}
	}

	/**
	 * @Description:获取所有可用的串口集合
	 */
	@SuppressWarnings("unchecked")
	protected static HashSet<CommPortIdentifier> getAvailableSerialPorts() {
		// 定义一个Set对象
		HashSet<CommPortIdentifier> hs = new HashSet<CommPortIdentifier>();
		// 获取一个枚举类型
		Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
		// 依次循环列出枚举类型
		while (portList.hasMoreElements()) {
			CommPortIdentifier com = (CommPortIdentifier) portList.nextElement();
			switch (com.getPortType()) {
			case CommPortIdentifier.PORT_SERIAL:
				try {
					// open:（应用程序名【随意命名】，阻塞时等待的毫秒数）
					/*
					 * open方法打开通讯端口，获得一个CommPort对象，它使程序独占端口。
					 * 如果端口正被其他应用程序占用，将使用CommPortOwnershipListener事件机制
					 * 传递一个PORT_OWNERSHIP_REQUESTED事件。
					 * 每个端口都关联一个InputStream和一个OutputStream,如果端口是用
					 * open方法打开的，那么任何的getInputStream都将返回相同的数据流对象，除非 有close被调用。
					 */
					CommPort thePort = com.open(Object.class.getSimpleName(), 50);
					thePort.close();
					//System.out.println("可用串口："+com.getName());
					hs.add(com);
				} catch (PortInUseException e) {
					// 不可用串口
					System.out.println("USB端口Port:" + com.getName() + " is InUse.");
				} catch (Exception e) {
					// 打开端口失败
					System.err.println("Failed to open port ： " + com.getName());
					e.printStackTrace();
				}
			}
		}
		return hs;
	}

	/**
	 * @Description:获取通信端口类型名称
	 * @author:
	 * @date:
	 */
	 public static String getPortTypeName(int portType) {
		switch (portType) {
		case CommPortIdentifier.PORT_I2C:
			return "I2C";
		case CommPortIdentifier.PORT_PARALLEL: // 并口
			return "Parallel";
		case CommPortIdentifier.PORT_RAW:
			return "Raw";
		case CommPortIdentifier.PORT_RS485: // RS485端口
			return "RS485";
		case CommPortIdentifier.PORT_SERIAL: // 串口
			return "Serial";
		default:
			return "unknown type";
		}
	} 
	// 将整数转换成为四位的16进制数的字符串
	public static String decimalToHex(int decimal) {
		String hex = "";
		while (decimal != 0) {

			char m;
			int hexValue = decimal % 16;
			if (hexValue <= 9 && hexValue >= 0) {
				m = (char) (hexValue + '0');
			} else {
				m = (char) (hexValue - 10 + 'A');
			}
			hex = m + hex;
			decimal = decimal / 16;
		}
		switch (hex.length()) {
		case 1:
			hex = "000" + hex;
			break;
		case 2:
			hex = "00" + hex;
			break;
		case 3:
			hex = "0" + hex;
			break;
		case 4:
			hex = hex + "";
			break;
		default:
			hex = "FFFF";
			break;
		}
		return hex;
	}

}
