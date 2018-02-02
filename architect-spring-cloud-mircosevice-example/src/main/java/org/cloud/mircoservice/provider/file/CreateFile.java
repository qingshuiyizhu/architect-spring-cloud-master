package org.cloud.mircoservice.provider.file;

import java.io.File;
import java.io.IOException;

public class CreateFile {
//	java 创建多级目录和文件
	public static void main(String[] args) {
		File file = new File("d:/bb/aa/dd/ff/s/test_file.txt");
		judeFileExists(file);

		File dir = new File("d:/cc/a/f/s/test_dir");
		judeDirExists(dir);
	}

	// 判断文件是否存在()
	public static void judeFileExists(File file) {

		if (file.exists()) {
			System.out.println("file exists");
		} else {
			System.out.println("file not exists, create it ...");
			try {
				  // 如果路径不存在,则创建  
	            if (!file.getParentFile().exists()) {  
	            	file.getParentFile().mkdirs();  
	            } 
				// 创建文件
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// 判断文件夹是否存在
	/*
	 * 1. 首先明确一点的是：test.txt文件可以和test文件夹同时存在同一目录下；test文件不能和test文件夹同时存在同一目录下。
	 * 
	 * 原因是：
	 * 
	 * （1）win的文件和文件夹都是以节点形式存放，这就意味着相同的文件和文件名不能处在同一目录下，会命名冲突。
	 * 
	 * （2）文件后缀名也算是文件名的一部分，即test.txt文件和test文件不是相同文件名的文件。
	 * 
	 * 
	 * 
	 * 2. 基于以上原因，如果我想在d:创建一个test文件夹，但是d:下面有一个test文件，那么由于命名冲突，是不可能创建成功的。
	 * 
	 * 所以，在创建之前，要通过file.exists()判断是否存在test命名的文件或者文件夹，如果返回true，是不能创建的；
	 * 
	 * 然后再通过file.isDirectory()来判断这是不是一个文件夹。
	 */
	public static void judeDirExists(File file) {
		if (file.exists()) {
			if (file.isDirectory()) {
				System.out.println("dir exists");
			} else {
				System.out.println("the same name file exists, can not create dir");
			}
		} else {
			// 创建文件夹
			 // 如果路径不存在,则创建  
            if (!file.getParentFile().exists()) {  
            	file.getParentFile().mkdirs();  
            } 
			System.out.println("dir not exists, create it ...");
			file.mkdir();
		}

	}

}
