package org.com.dev.config;

import org.com.dev.util.FTPUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
@Component
@Configuration
@PropertySource(value = "classpath:ftp.properties", ignoreResourceNotFound = true)
public class FTPConfig {
	@Value("${ftp.ip}")
	private String ip;

	@Value("${ftp.port}")
	private Integer port;

	@Value("${ftp.userName}")
	private String userName;

	@Value("${ftp.userPassword}")
	private String userPassword;
   
	private static FTPUtil ftpUtil =null;
	public FTPUtil ftpUtils(){
		 if(ftpUtil ==null){
			 ftpUtil = new FTPUtil(ip, port, userName, userPassword);
		 }
		 return ftpUtil;
	}
	
	
}
