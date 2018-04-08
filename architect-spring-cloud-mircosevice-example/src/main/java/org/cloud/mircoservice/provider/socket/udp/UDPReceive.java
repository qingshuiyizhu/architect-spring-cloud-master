package org.cloud.mircoservice.provider.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: UDPClientDemo
 * @Description: TODO UDP 接收端
 * @author LINGHUI
 * @date 2017年6月7日
 *步骤：
 *1.创建一个字节数组
 *2.创建一个数据报包对象
 *3.根据监听端口号创建UDP协议的Socket对象
 *4.调用udp协议的socket对象的receive方法接收数据
 *5.将接收到的数据转换为java对象类型
 *6.关闭socket
 *
 *
 */
public class UDPReceive {
	public static void main(String[] args) {
		//1.创建一个字节数组存储接收到的数据
		// 数组长度最大为64K，1024为1K
		byte b[] = new byte[1024];
		// 2.创建一个数据报包对象
	 	DatagramPacket dp = new DatagramPacket(b, b.length);
        int i = 0;	
      	try {
			//3. 根据监听端口号创建UDP协议的Socket对象
			//读取文件
	        DatagramSocket ds = new DatagramSocket(5000);// 在此端口上进行监听;
			while(true){
		 	System.out.println("接受端正在等待接收数据。。。");
		 	//4.调用udp协议的socket对象的receive方法接收数据
			ds.receive(dp);// 接收数据，该方法会造成阻塞
			//5.将接收到的数据转换为Java对象类型
			String info = new String(dp.getData(), 0, dp.getLength());
			System.out.println(info);
			//6.关闭socket 调用UDP协议Socket对象的close方法
			// ds.close();
		 	List<Socket11> dd = new ArrayList<>();
		 	Socket11 sc = new Socket11();
	   	    sc.setDs(ds);
			dd.add(sc);	 
     	 	dp.setData("I am server!!!".getBytes());
     	   //	ds = new DatagramSocket(5000);
			// ds.send(dp);
     	 	 dd.get(0).getDs().send(dp);
			}	
		} catch (Exception e) {
		}

		
	}
}
