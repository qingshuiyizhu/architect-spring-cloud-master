package org.com.dev.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*@Configuration
@ComponentScan
@EnableAutoConfiguration
public class WebAppRootContext implements ServletContextInitializer {
	private Logger logger=LoggerFactory.getLogger(WebAppRootContext.class); 
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		    logger.info("启动加载自定义的MyServletContainerInitializer");  
	        System.out.println("启动加载自定义的MyServletContainerInitializer");  
	         ServletRegistration.Dynamic testServlet = servletContext.addServlet("dwr-invoker","org.directwebremoting.servlet.DwrServlet");  
	        testServlet.setInitParameter("debug", "true"); 
	        testServlet.setLoadOnStartup(2);  
	        testServlet.addMapping("/dwr/*");   
 	     
}

}*/
		 
		/*
		 * 	<!-- dwr的配置 -->
	<servlet>
		<servlet-name>dwr-invoker</servlet-name>
		<servlet-class>org.directwebremoting.servlet.DwrServlet</servlet-class>
		<init-param>
			<param-name>debug</param-name>
			<param-value>true</param-value>
		</init-param>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>classpath:springmvc-servlet.xml</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>

	<servlet-mapping>
		<servlet-name>dwr-invoker</servlet-name>
		<url-pattern>/dwr/*</url-pattern>
	</servlet-mapping>
		 */
	        
	        
	      /*  <!-- 当Spring容器启动完成后执行下面的这个Bean --> 
	        <bean id="templateAnnotationInit" class="com.web.ccs.socket.ParseTemplateAnnotationHandler"  init-method="initMethod" destroy-method="destroyMethod"/>  
	        */ 
    
