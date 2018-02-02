package org.cloud.mircoservice.provider.utils;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class Freemarker {

	public static void main(String[] args) throws Exception {
		// 创建Freemarker配置实例
		// 创建数据模型
		// 加载模版文件
		// 显示生成的数据

		// 1、创建Freemarker配置实例
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File("templates"));

		// 2、创建数据模型(树状结构)
		Map root = new HashMap();
		root.put("user", "老高");

		// 3、加载模版文件
	//	  Template t1 = cfg.getTemplate();
		
		// 4、显示生成的数据
	 	Writer out = new OutputStreamWriter(System.out);
	// 	t1.process(root,out);
		out.flush();
		out.close();
		
	}

}
