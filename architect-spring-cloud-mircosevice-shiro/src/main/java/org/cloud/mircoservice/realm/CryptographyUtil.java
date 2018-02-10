package org.cloud.mircoservice.realm;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;

/*
 * BASE 严格地说，属于编码格式，而非加密算法
   MD(Message Digest algorithm ，信息摘要算法)
   SHA(Secure Hash Algorithm，安全散列算法)
   HMAC(Hash Message Authentication Code，散列消息鉴别码)
   bcrypt
 */
public class CryptographyUtil {
	public static String encBase64(String str) {
		return Base64.encodeToString(str.getBytes());
	}

	public static String decBase64(String str) {
		return Base64.decodeToString(str);
	}

	public static String md51(String str, String salt) {
		System.out.println("Md5加密：" + new Md5Hash(str, salt).toString());
		return new Md5Hash(str, salt).toString();
	}

	public static String md5(String str) {
		return new Md5Hash(str, "caonidaye").toString();
	}
	
	
	
	
	
	
	
	
	
	
	
}
