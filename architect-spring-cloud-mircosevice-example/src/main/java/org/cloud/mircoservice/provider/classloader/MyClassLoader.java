package org.cloud.mircoservice.provider.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
//自定义类加载器
public class MyClassLoader extends ClassLoader {
	private String name; // 类加载器的名字
	private String path = "d:\\";// 加载类的路径
	private final String fileType = ".class";// class文件的扩展名

	public MyClassLoader(String name) {
		super();// 让系统类加载器成为该类加载器的父加载器
		this.name = name;
	}

	public MyClassLoader(ClassLoader parent, String name) {
		super(parent);// 显示指定该类加载器的父加载器
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;

	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public  Class<?> findClass(String name) throws ClassNotFoundException {
 		byte[] data = this.loadClassData(name);
		return this.defineClass(name, data, 0,data.length);
	}
	
	
	private byte[] loadClassData(String name) {
		InputStream is = null;
		byte[] data = null;
		ByteArrayOutputStream baos = null;
		try {
			this.name = this.name.replaceAll(".", "\\");
			is = new FileInputStream(new File(path + name + fileType));
			baos = new ByteArrayOutputStream();
			int ch = 0;
			while (-1 != (ch = is.read())) {
				baos.write(ch);
			}
			data = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace( );
		}finally {
			try {
			is.close();
			baos.close();
			} catch (Exception e2) {
 			}
		}
 	return data;

	}

	
	public static void main(String[] args) throws Exception {
		MyClassLoader loader1 = new MyClassLoader("loader1");
		loader1.setPath("d:\\myapp\\serverlib");
		//loader2->loader1加载器->系统类加载器->扩展类加载器->根类加载器
		MyClassLoader loader2 = new MyClassLoader(loader1,"loader2");
		loader2.setPath("d:\\myapp\\clientlib");
		//loader3的父加载器是根类加载器
		MyClassLoader loader3 = new MyClassLoader(null,"loader3");
		
		//2.通过反射取值
	Class clazz = loader1.loadClass("Sample");
	Object object = clazz.newInstance();//创建一个Sample类的对象
	Field field = clazz.getField("v1");
	int v1 = field.getInt(object);
	System.out.println("v1:"+v1);
		
		
	}
	public static void test(ClassLoader loader) throws Exception {
		Class clazz = loader.loadClass("org.cloud.mircoservice.provider.classloader.Sample");
		Object object = clazz.newInstance();
		
	}
	 
	
}
