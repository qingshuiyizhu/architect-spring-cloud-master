package org.cloud.mircoservice.provider.io;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;

//String与InputStream相互转换 
public class ConvertStreamToString {
	/*
	 * 1.String to InputStream
	 * 
	 * String str = "String与InputStream相互转换";
	 * 
	 * InputStream in_nocode = new ByteArrayInputStream(str.getBytes());
	 * InputStream in_withcode = new
	 * ByteArrayInputStream(str.getBytes("UTF-8"));
	 */

	//2.InputStream to String 这里提供几个方法。
 	//方法1：
 	public String convertStreamToString(InputStream is) {
   	BufferedReader reader = new BufferedReader(new InputStreamReader(is));
  	StringBuilder sb = new StringBuilder();
 	String line = null;
 	try {
 		while ((line = reader.readLine()) != null) {
  		sb.append(line + "/n");
  		}
 	} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				is.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

		return sb.toString();

	}
	//方法2：
  	public String inputStream2String(InputStream in) throws IOException {
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n));
		}
		return out.toString();
	}
	//方法3：
	public static String inputStream3String(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}

	// 将输入流InputStream转换为String
	// 推荐一个jar包，用来转换InputStream到String
	public static String convertStreamToString1(InputStream in) {
		String result = null;
		try {
			result = IOUtils.toString(in, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String convertStreamToString2(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}
}
