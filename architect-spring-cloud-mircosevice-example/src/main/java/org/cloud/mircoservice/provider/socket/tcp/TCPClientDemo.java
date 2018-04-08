package org.cloud.mircoservice.provider.socket.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
    * @ClassName: ClientDemo
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author LINGHUI
    * @date 2017年6月7日
    * 
    * 客户端
    * public class Socket extends Object 
    * 此类实现客户端套接字，套接字是两台机器之间通信的端点。
    * Socket(String host,int port)
    *       创建一个流套接字并将其连接到指定主机上的指定端口号。
    *       Input Stream getInputStream()
    *       返回此套接字的输入流。
    *       OutputStream getOutputStream()
    *       返回此套接字的输出流。
    *       
    *       void setSoTimeout(int timeout)
    *       启用/禁用带有指定超时值的SO_TIMEOUT,以毫秒为单位。
    *
 */
 
public class TCPClientDemo {
	public static void main(String[] args) {
   	try {
			//1.创建一个客户端的socket对象，IP/主机名 端口号
			Socket s =  new Socket("192.168.1.117",8888);
			System.out.println("与服务器连接成功！");
			//2.创建一个BufferedReader 存储 socket接收到的InputStream
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			//3.将BufferedReader对象进行转换
			String info = br.readLine();
			System.out.println(info);
			//4.关闭BufferedReader  
			br.close();
	 	} catch (UnknownHostException e) {
	 		e.printStackTrace();
		} catch (IOException e) {
	 		e.printStackTrace();
		}
		
		
	}

}
