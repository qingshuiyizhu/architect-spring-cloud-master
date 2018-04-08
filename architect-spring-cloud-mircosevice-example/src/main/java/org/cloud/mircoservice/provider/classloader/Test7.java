package org.cloud.mircoservice.provider.classloader;

/*调用ClassLoader类的loadClass方法加载一个类，
 *  并不是对类的主动使用，不会导致类的初始化。
 */
class CL {
	static {
		System.out.println("class CL");
	}
}

public class Test7 {

	public static void main(String[] args) throws ClassNotFoundException {
		//获得系统类加载器
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		Class<?> clazz = loader.loadClass("org.cloud.mircoservice.provider.classloader.CL");
		System.out.println("------------------------------------");
		//反射
		clazz = Class.forName("org.cloud.mircoservice.provider.classloader.CL");
 
	}
}
