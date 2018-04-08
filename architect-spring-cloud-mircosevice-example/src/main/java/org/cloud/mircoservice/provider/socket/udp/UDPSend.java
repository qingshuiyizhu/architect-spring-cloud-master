package org.cloud.mircoservice.provider.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPSend {
	/*
	 * UDP 发送端 无连接协议
	 * 1.把发送数据转换成字节数据
	 * 2.新建数据报包对象 参数为：要发送的字节数组，数组的起始位置，数组的长度，请求地址：端口号
	 * 3.根据发送端的端口创建一个UDP协议的Socket对象
	 * 4.调用Socket对象发送数据
	 * 5.调用Socket对象的close方法关闭socket
	 * 
	 * 
	 */
	public static void main(String[] args) throws InterruptedException {
		String info = "ABCDEFG12345678";
		// 1.把数据转换成字节数据
		byte[] bytes = info.getBytes();
		try {
			// 2.新建一个数据报包对象    参数为： 要发送的字节数组，数组起始位置，数组的长度，InetAddress：请求地址，接受端的端口号
			    //接收端在5000端口监听,接受端的端口号
			DatagramPacket data = new DatagramPacket(bytes, 0, bytes.length, InetAddress.getByName("127.0.0.1"),5000);
			// 3.创建一个UDP协议的Socket对象   //发送端在3000端口监听 
			DatagramSocket ds = new DatagramSocket(3000);
			//4.发送数据
			ds.send(data);//发送完毕
			System.out.println("消息发送完毕！");
			//5.关闭socket 调用UDP协议Socket对象的close方法
			// ds.close();	
			
			
			
			
			 System.out.println("12"+ds.getPort());	       
			while(true){
		 		 //接收数据
	            DatagramPacket inputPacket = new DatagramPacket(new byte[512], 512);  
	            ds.receive(inputPacket);  
	            System.out.println(new String(inputPacket.getData(), 0 , inputPacket.getLength()));
	           // ds.close();
	            Thread.sleep(5000);
	            System.out.println(ds.getPort());
	           // ds.close();		
			}
	        
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	   
	}

}
