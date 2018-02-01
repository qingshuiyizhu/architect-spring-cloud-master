package org.cloud.mircoservice.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 清除代码中的注释（不是所有）
 *
 */

public class ClearComment {
	/** 项目根目录 */
	public static String rootDir = "D:\\Projects\\gxg\\gxg_server";

	public static void main(String args[]) {
		deepDir(rootDir);
		System.out.println("！！！！！！！！！！！！！！！！项目代码注释已经清除完毕！！！！！！！！！！！！！！！！");
	}

	public static void deepDir(String rootDir) {
		File folder = new File(rootDir);
		if (folder.isDirectory()) {
			String[] files = folder.list();
			for (int i = 0; i < files.length; i++) {
				File file = new File(folder, files[i]);
				if (file.isDirectory() && file.isHidden() == false) {
					deepDir(file.getPath());
				} else if (file.isFile()) {
					clearComment(file);
				}
			}
		} else if (folder.isFile()) {
			clearComment(folder);
		}
	}

	public static void clearComment(File file) {
		Map<String, String> patterns = new HashMap<String, String>();

		System.out.println("开始清除代码注释的文件为:" + file.getPath());
		if (file.getName().endsWith(".java") || file.getName().endsWith(".js") || file.getName().endsWith(".css")) {
			// patterns.put("([^:])\\/\\/.*", "$1");//
			// 匹配在非冒号后面的注释，此时就不到再遇到http://
			patterns.put("\\s+\\/\\/.*", "");// 匹配“//”前是空白符的注释
			patterns.put("\\s/\\*+([\\s\\S]|[^/]*)\\*+/\\s", "");
			clearFileComment(file.getPath(), patterns);
		}
		if (file.getName().endsWith(".html") || file.getName().endsWith(".htm") || file.getName().endsWith(".xml")
				|| file.getName().endsWith(".jsp") || file.getName().endsWith(".ftl")) {
			patterns.put("<!--[^\\!\\[]*?(?<!\\/\\/)-->", "");
			clearFileComment(file.getPath(), patterns);
		}
		System.out.println("完成清除代码注释的文件为:" + file.getPath());
	}

	public static void clearFileComment(String filePathAndName, Map<String, String> patterns) {
		try {
			StringBuffer buffer = new StringBuffer();
			String line = null; // 用来保存每行读取的内容
			InputStream is = new FileInputStream(filePathAndName);
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			line = reader.readLine();
			// 读取第一行
			while (line != null) { // 如果 line 为空说明读完了
				buffer.append(line); // 将读到的内容添加到 buffer 中
				buffer.append("\r\n"); // 添加换行符
				try {
					line = reader.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				} // 读取下一行
			}
			String filecontent = buffer.toString();
			Iterator<String> keys = patterns.keySet().iterator();
			String key = null, value = "";
			while (keys.hasNext()) {
				// 经过多次替换
				key = keys.next();
				value = patterns.get(key);
				filecontent = replaceAll(filecontent, key, value);
			}
			// 再输出到原文件
			try {
				File f = new File(filePathAndName);
				if (!f.getParentFile().exists()) {
					f.getParentFile().mkdirs();
				}
				FileOutputStream out = new FileOutputStream(filePathAndName);
				byte[] bytes = filecontent.getBytes("UTF-8");
				out.write(bytes);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {

		}

	}

	/**
	 * @param fileContent
	 *            内容
	 * @param patternString
	 *            匹配的正则表达式
	 * @param replace
	 *            替换的内容
	 * @return
	 */
	public static String replaceAll(String fileContent, String patternString, String replace) {
		String str = "";
		Matcher m = null;
		Pattern p = null;
		try {
			p = Pattern.compile(patternString);
			m = p.matcher(fileContent);
			str = m.replaceAll(replace);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			m = null;
			p = null;
		}
		// 获得匹配器对象
		return str;

	}
}
