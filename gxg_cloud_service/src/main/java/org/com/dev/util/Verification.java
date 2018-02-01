package org.com.dev.util;

public class Verification {

	// 字符串是否为null或者为""

	public static boolean StrNoEmpty(String str) {
		if (str == null || ""== str || "".equals(str)) {
			return false;
		} else {
			return true;
		}

	}

}
