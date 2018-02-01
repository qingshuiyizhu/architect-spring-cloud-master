package org.com.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
/*@Configuration  
@EnableWebMvc   //开启Spring MVC支持，若无此句，重写WebMvcConfigurerAdapter方法无效  
@ComponentScan("jack.springmvc")  
//继承WebMvcConfigurerAdapter类，重写其方法可对Spring MVC进行配置  
public class MVCConfig  extends WebMvcConfigurerAdapter{
	   @Bean  
	    public InternalResourceViewResolver viewResolver(){  
	        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();  
	        //viewResolver.setPrefix("/WEB-INF/classes/views/");  
	        viewResolver.setPrefix("/WEB-INF/classes/views/");  
	        viewResolver.setSuffix(".jsp");  
	        viewResolver.setViewClass(JstlView.class);  
	       
	        return  viewResolver;  
	    }  
} */