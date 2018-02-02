package org.cloud.mircoservice.provider.utils;
/*
 * 
    * @ClassName: Verification
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author LINGHUI
    * @date 2017年7月27日
    *
 */
public class Verification {
 	// 字符串是否为null或者为""
    public static boolean StrNotEmpty(String str) {
		if (str == null || ""== str || "".equals(str)||str.length()==0) {
			return false;
		} else {
			return true;
		}
	}
    
   
    public static boolean StrIsEmpty(String str) {
		 return !StrNotEmpty(str);
	}
    
}
