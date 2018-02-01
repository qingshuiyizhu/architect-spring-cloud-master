package org.com.dev.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.com.dev.webservice.MyWebService;
import org.com.dev.webservice.MyWebServiceImpl;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class CxfBean {

	/*@Bean
	public ServletRegistrationBean restServlet(){
	  //注解扫描上下文
	  AnnotationConfigWebApplicationContext applicationContext
	      = new AnnotationConfigWebApplicationContext();
	  //base package
	  applicationContext.scan("org.com.dev.webservice");
	  //通过构造函数指定dispatcherServlet的上下文
	  DispatcherServlet rest_dispatcherServlet
	      = new DispatcherServlet(applicationContext);
	 
	  //用ServletRegistrationBean包装servlet
	  ServletRegistrationBean registrationBean
	      = new ServletRegistrationBean(rest_dispatcherServlet);
	  registrationBean.setLoadOnStartup(1);
	  //指定urlmapping
	  registrationBean.addUrlMappings("/rest/*");
	  //指定name，如果不指定默认为dispatcherServlet
	  registrationBean.setName("rest");
	  return registrationBean;
	}*/
	
     @Bean
	public ServletRegistrationBean cxfServlet(){
	  //注解扫描上下文
	/*  AnnotationConfigWebApplicationContext applicationContext
	      = new AnnotationConfigWebApplicationContext();*/
	  //base package
	//  applicationContext.scan("org.com.dev.webservice");
	  //通过构造函数指定dispatcherServlet的上下文
	/*  DispatcherServlet rest_dispatcherServlet
	      = new DispatcherServlet(applicationContext);*/
	 
	  //用ServletRegistrationBean包装servlet
	  ServletRegistrationBean cxfBean
	      = new ServletRegistrationBean(new CXFServlet(), "/webservice/*");
	  cxfBean.setLoadOnStartup(1);
	  //指定urlmapping
	//  registrationBean.addUrlMappings("/rest/*");
	  //指定name，如果不指定默认为dispatcherServlet
	  cxfBean.setName("cxfBean");
	  return cxfBean;
	}
 
     @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), myWebService());
        endpoint.publish("/soap");
      
      //打印报文日志拦截器
      //  endpoint.getInInterceptors().add(new LoggingInInterceptor());
      // endpoint.getInInterceptors().add(new LoggingOutInterceptor());

     //通过拦截器校验用户名与密码
     //endpoint.getInInterceptors().add(new AuthInterceptor());
    return endpoint;

    }
     @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }
    @Bean
    public MyWebService myWebService() {
        return new MyWebServiceImpl();
    } 
	
}
