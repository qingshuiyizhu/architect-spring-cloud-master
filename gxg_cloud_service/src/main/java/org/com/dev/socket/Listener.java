package org.com.dev.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import javax.annotation.PreDestroy;

import org.com.dev.service.AsyncTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class Listener implements ApplicationListener<ApplicationReadyEvent> {

	private static final Logger logger = LoggerFactory.getLogger(Listener.class);
	@Autowired
	private AsyncTaskService asyncTaskService;
 	// spring 容器开始时调用方法
	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		//
		System.out.println("调用客户端监听方法");
		asyncTaskService.ListenerClient();
		System.out.println("调用客户端监听方法完毕！");
	}
	
	
	
	// spring-boot退出时执行操作
	@PreDestroy
	public void destory() {
	     System.out.println("中控端接收到心跳数据的SocketThread服务退出！");
		 logger.error("中控端接收到心跳数据的SocketThread服务退出！");
	}
	
}