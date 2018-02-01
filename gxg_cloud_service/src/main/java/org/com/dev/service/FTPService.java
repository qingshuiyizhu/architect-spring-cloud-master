package org.com.dev.service;

import java.io.InputStream;

import org.apache.commons.io.FilenameUtils;
import org.com.dev.config.FTPConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class FTPService {
@Autowired
private FTPConfig ftp;
 	// 获取ftp上的所有文件名和文件夹
	public String getFileTozTree() {
		ftp.ftpUtils().openFTP();
 	 
	 	String data = JSON.toJSONString(ftp.ftpUtils().getFileTozTree("\\", 0), true).replaceAll("parent", "isParent");
	 	ftp.ftpUtils().closeFTP();
		System.out.println(data);
		return data;
	}

	// 删除ftp服务器上的文件或者文件夹

	public boolean zTreeOnRemove(String path) {
		 
		ftp.ftpUtils().openFTP();
		boolean sign = ftp.ftpUtils().deleteIterate(path);
		ftp.ftpUtils().closeFTP();
		return sign;

	}

	// 上传文件到ftp
	 
	public boolean uploadFile(InputStream inputStream, String fileName, String path){
		 
		String tempFileName = FilenameUtils.getName(fileName);
		ftp.ftpUtils().openFTP();
		boolean sign = ftp.ftpUtils().uploadFile(path, tempFileName, inputStream);
	 	ftp.ftpUtils().closeFTP();
	 
		return sign;
	}

	public boolean creatDir(String path, String name) {
		ftp.ftpUtils().openFTP();
		boolean sign = ftp.ftpUtils().createDir(path, name);
		ftp.ftpUtils().closeFTP();
	 	return sign;
	}
	  
}
