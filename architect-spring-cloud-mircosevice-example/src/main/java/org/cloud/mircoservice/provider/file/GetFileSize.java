package org.cloud.mircoservice.provider.file;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;

public class GetFileSize {
	
	
	//返回的实际可读字节数，也就是总大小
	public long getFileSizes(File f) throws Exception{
	       long s=0;
	       if (f.exists()) {
	           FileInputStream fis = null;
	           fis = new FileInputStream(f);
	          s= fis.available();
	       } else {
	           f.createNewFile();
	           System.out.println("文件不存在");
	       }
	       return s;
	    }
	
	
	
	
	
	
	// 转换文件大小
	public String FormetFileSize(long fileS) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}
}
