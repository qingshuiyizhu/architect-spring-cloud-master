
package org.cloud.mircoservice.provider.app;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
/*
@ServletComponentScan // 扫描使用注解方式的servlet
@EnableAutoConfiguration //能够自动配置spring的上下文，试图猜测和配置你想要的bean类，通常会自动根据你的类路径和你的bean定义自动配置。
@ComponentScan(basePackages = { "org.com.dev.controller" ,"org.com.dev.service","org.com.dev.config"})//扫描包注解
@EnableJpaRepositories(basePackages = "org.com.dev.repository") //扫描jpa 的repository包
@EntityScan("org.com.dev.entity") //扫描实体类
@EnableCaching  //开启缓存
@EnableAsync 开启异步任务
*/

@SpringBootApplication
//@SpringBootApplication = (默认属性)@Configuration + @EnableAutoConfiguration + @ComponentScan。

public class Application {
	  public static void main(String[] args) {
	 	SpringApplication.run(Application.class, args);
	}   

}  
