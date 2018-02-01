package org.com.dev.config;
/*
import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.com.dev.webservice.MyWebService;
import org.com.dev.webservice.MyWebServiceImpl;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

//@Configuration
 
 //此方法会覆盖默认的dispatcherServlet 
  
public class CxfConfig1 {
    @Bean
    public ServletRegistrationBean dispatcherServlet() {
    	ServletRegistrationBean cxfBean =  new ServletRegistrationBean(new CXFServlet(), "/webservice/user/*");
    	 cxfBean.setLoadOnStartup(2);
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
    
} */