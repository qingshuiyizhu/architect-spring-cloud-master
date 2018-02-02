package org.cloud.mircoservice.provider.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.ExitCodeGenerator;

//除了注解@PreDestroy 的销毁方法，还可以通过实现DisposableBean, ExitCodeGenerator的方式
public class BeforeStopDestroy implements DisposableBean, ExitCodeGenerator {
	private Logger logger=LoggerFactory.getLogger(BeforeStopDestroy.class); 
	@Override
	public void destroy() throws Exception {
	 	logger.info("<<<<<<<<<<<我被销毁了......................>>>>>>>>>>>>>>>");
	}

	@Override
	public int getExitCode() {

		return 5;
	}
}

/*
  PS：还有一些spring内置的事件
1、  ContextRefreshedEvent：ApplicationContext容器初始化或者刷新时触发该事件。

2、  ContextStartedEvent：当使用ConfigurableApplicationContext接口的start()方法启动ApplicationContext容器时触发该事件。

3、  ContextClosedEvent：当使用ConfigurableApplicationContext接口的close()方法关闭ApplicationContext容器时触发该事件。

4、  ContextStopedEvent: 当使用ConfigurableApplicationContext接口的stop()方法停止ApplicationContext容器时触发该事件。
*/