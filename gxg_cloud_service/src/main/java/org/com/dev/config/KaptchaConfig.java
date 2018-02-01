package org.com.dev.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
 //google kaptcha的相关配置 
@Configuration
public class KaptchaConfig {
        @Bean // 声明其为Bean实例
	     public DefaultKaptcha  captchaProducer(){
        	 DefaultKaptcha  captchaproducer = new DefaultKaptcha();
        	 Properties properties = new Properties();
        	 /*
         <!-- 是否有边框 可选yes 或者 no --> 
 8                         <prop key="kaptcha.border">yes</prop>  
 9                         <!-- 边框颜色 -->
10                         <prop key="kaptcha.border.color">105,179,90</prop>  
11                         <!-- 验证码文本字符颜色 -->
12                         <prop key="kaptcha.textproducer.font.color">blue</prop>  
13                         <!-- 验证码文本字符大小 -->
14                         <prop key="kaptcha.textproducer.font.size">45</prop>  
15                         <!-- 验证码图片的宽度 默认200 -->
16                         <prop key="kaptcha.image.width">125</prop>  
17                         <!-- 验证码图片的高度 默认50 -->
18                         <prop key="kaptcha.image.height">45</prop>  
19                         <!-- 验证码文本字符长度  默认为5 -->
20                         <prop key="kaptcha.textproducer.char.length">4</prop>  
21                         <!-- 验证码文本字体样式  默认为new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)  -->
22                         <prop key="kaptcha.textproducer.font.names">宋体,楷体,微软雅黑</prop>  
        */
        	 properties.put("kaptcha.border", "yes");
        	 properties.put("kaptcha.border.color", "105,179,90");
        	 properties.put("kaptcha.textproducer.font.color", "red");
        	 properties.put("kaptcha.textproducer.font.size", "30");
        	 properties.put("kaptcha.image.width", "100");
        	 properties.put("kaptcha.image.height", "40");
        	 properties.put("kaptcha.textproducer.char.length", "4");
        	// properties.put("kaptcha.textproducer.font.names", "宋体,楷体");
         	 Config config = new Config(properties);
         	captchaproducer.setConfig(config);
	    	
			return captchaproducer;
	 	    	 
	     } 
        
	
	
	
	
	
	
	
}
