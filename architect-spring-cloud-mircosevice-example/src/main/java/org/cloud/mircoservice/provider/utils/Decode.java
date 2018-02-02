package org.cloud.mircoservice.provider.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Decode {

	public static void main(String[] args) throws UnsupportedEncodingException {
	 
		String fName="%e6%8e%88%e6%9d%832";
		String decodeFName = new String(fName.getBytes("iso-8859-1"),"utf-8");
		System.out.println(decodeFName);
		//name = URLDecoder.decode(name, "UTF-8");  
		//servlet返回数据时，将字符编码设置为utf-8就可以了，因为Ajax只支持utf-8  
		String result = URLDecoder.decode("0711-E-%E7%90%B4%E9%94%AE.mov", "UTF-8");
   	  System.out.println(result);
	}

}
