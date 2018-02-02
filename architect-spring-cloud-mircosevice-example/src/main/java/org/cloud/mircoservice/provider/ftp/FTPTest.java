package org.cloud.mircoservice.provider.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
public class FTPTest {

	public static void main(String[] args) throws IOException {
		
	/*	
	 	String plays = "A,H,J,K,B,H,J,K,C,H,J,K,D,H,J,K,E,H,J,K,F,G";
    //  String plays = "A,B,C,C";
		String[] file = plays.split(",");
		// System.out.print(file[0]);
		for (int i =0; i <file.length; i++) {
		 	int a = 1;
 			for (int j = 0; j<file.length; j++) {
 				if(i!=j){
 				    	 if(file[i].equals(file[j])){
 	 			  		file[j] = file[j]+String.valueOf(a);
 						a++;
 			 	 	} 
 				}
 				
	 		}
			
		} 	
		for (int i = 0; i < file.length; i++) {
			System.out.print("-"+file[i]);
		}*/
		
		
		
		
		//copyFile("D:/gxg_program/ftpDownload/A立体方块.mp4","D:/gxg_program/ftpDownload/A立体方块1.mp4");
		
		
		
		
		
		
		
		
      FTPUtil ftpUtil = new FTPUtil("119.23.155.183", 21, "lh", "Lh123");
    ftpUtil.openFTP();
  //  ftpUtil.deleteFile("/gxgftp");
    ftpUtil.downFile("/gxgftp", "砰.mov", "D:/");
   // FTPFile[] list =  ftpUtil.getFileListDirectories("/");
  //  ftpUtil.getFileListAll("/");
  /*  for (FTPFile ftpFile : list) {
    	System.out.println(ftpFile.getName()+ftpFile.getSize());
  	}*/
    ftpUtil.closeFTP();
   
	}

	
	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 */
	public static void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
			   //	int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();
		}
	}
	
}
