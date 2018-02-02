package org.cloud.mircoservice.provider.event;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

//软件开启之后执行操作
@Component
public class ApplicationStartupAfter  implements ApplicationListener<ApplicationReadyEvent>  {
	private Logger logger=LoggerFactory.getLogger(ApplicationStartupAfter.class); 
	//软件开始后调用方法
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		logger.info("软件开启后，调用onApplicationEvent。。。。。。。。。。。");
		
	}
 	   //软件退出前执行销毁操作
		@PreDestroy
		public void destory() {
 		logger.info("软件关闭前，调用@PreDestroy>>>>destory。。。。。。。。。。。");	
			
		}
	
}
