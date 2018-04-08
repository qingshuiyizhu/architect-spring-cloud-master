package org.cloud.mircoservice.provider.socket.tcp;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
/*
 * 
TCP程序概述
TCP 是一个可靠的协议，面向连接的协议。
实现TCP程序，需要编写服务器端和客户端。JavaAPI为我们提供了java.net包.为实现网络应用提供类。
ServerSocket：此类实现服务器的套接字。
Socket：此类实现客户端套接字   Socket 是网络驱动层提供给应用程序编程的接口和一种机制。

服务器端：
public class ServerSocket extends Object
此类实现服务器套接字。服务器套接字等待请求通过网络传入。它基于该请求执行某些操作，然后可能向请求者返回结果。

ServerSocket（int port）
创建绑定到特定端口的服务器套接字。

void setSoTimeout(int timeout)
 通过指定超时值启用/禁用SO_TIMEOUT
 以毫秒为单位。
 
 InetAddress getInetAddress()
 返回此服务器套接字的本地地址
 Socket accept()
        侦听并接受到此套接字的连接。
 * 
 */
public class ServerDemo {

	/**
	 * 
	    * @Title: main
	    * @Description: TODO TCP 服务器程序
	    * @param @param args    参数
	    * @return void    返回类型
	    * @throws
	    * 1.根据端口创建一个服务器端的Socket
	    * 2.调用Socket的accept方法，等待客户端连接，会阻塞
	    * 3.根据服务器端socket对象构造BufferedWriter
	    * 4.调用Bufferedwriter对象的write方法，将需要发送的数据写入
	    * 5.关闭Bufferwriter
	    * 
	    * 
	    * 
	 */
	
	
	public static void main(String[] args) {
             
		try {
			//1.创建一个服务器端的Socket   端口 port：1024-65535
			ServerSocket  server =  new ServerSocket(8888);
			System.out.println("服务器已启动,正在等待连接中。。。");
			//2.调用Socket的accept方法，等待客户端连接，会阻塞
			Socket s = server.accept();
			//字节流->字符流
	 	    //构造一个字符输出流
			//3.根据服务器端socket对象构造BufferedWriter
		    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
     	    String info = "亲，你的宝贝已发出，请注意查收！\n";
	 	   //4.调用Bufferedwriter对象的write方法，将需要发送的数据写入
     	    bw.write(info);
     	    //5.关闭Bufferedwriter
	 	   bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
