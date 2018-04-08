package org.cloud.mircoservice.provider.classloader;
/*
 1、ClassLoader(类)
 
 
 
 
 */
public class Test1 {
public static void main(String[] args) throws ClassNotFoundException {
	//根类加载器加载
	
	Class clazz = Class.forName("java.lang.String");
	System.out.println(clazz.getClassLoader());
	
	
	  //系统加载器加载
	Class clazz2 = Class.forName("org.cloud.mircoservice.provider.classloader.C");
	System.out.println(clazz2.getClassLoader());
}
	
	
	
	
	
}

