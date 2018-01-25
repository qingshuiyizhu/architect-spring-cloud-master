package org.cloud.mircoservice.log;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


/**
 * 部署到外部的Tomcat容器中：修改启动类，继承 SpringBootServletInitializer 并重写 configure 方法
 * @author 
 *
 */
/*@SpringBootApplication
@ServletComponentScan // 扫描使用注解方式的servlet
@ComponentScan(basePackages = { "org.com.dev.controller" ,"org.com.dev.service","org.com.dev.socket","org.com.dev.config"})
@EnableJpaRepositories(basePackages = "org.com.dev.repository")
@EntityScan("org.com.dev.entity")
@EnableCaching
@EnableAsync */
  public class SpringBootStartApplication extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootStartApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    	logger.info("---------------------------------------------------------------------------");
    	logger.info("以外部的Tomcat容器启动云连控3.0中控端！"+new Date());
        return builder.sources(Application.class);    
    }

}   