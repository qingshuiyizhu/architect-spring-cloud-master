
package org.cloud.mircoservice.log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/*@ComponentScan(basePackages = { "org.com.dev.controller" })
@EnableJpaRepositories(basePackages = "org.com.dev.repository")
@EntityScan("org.com.dev.entity")
@EnableAutoConfiguration 
*/

@SpringBootApplication
//@EntityScan("org.cloud.mircoservice.entity")

//@EnableJpaRepositories(basePackages = "org.cloud.mircoservice.provider.repository")
public class Application {
	  public static void main(String[] args) {
	 	SpringApplication.run(Application.class, args);
	}   

}  
