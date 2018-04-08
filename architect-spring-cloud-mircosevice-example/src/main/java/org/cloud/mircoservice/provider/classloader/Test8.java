package org.cloud.mircoservice.provider.classloader;

public class Test8 {
public static void main(String[] args) {
	ClassLoader classLoader = ClassLoader.getSystemClassLoader();
	System.out.println(classLoader);
	while( null != classLoader) {
		classLoader = classLoader.getParent();
		System.out.println(classLoader);
		//sun.misc.Launcher$AppClassLoader@73d16e93 系统类加载器
		//sun.misc.Launcher$ExtClassLoader@15db9742 扩展类加载器
		//null 根类加载器
		打印();
	}
	

}
public static void 打印() {
	System.out.println("打印。。。。。。。。。");
}
}
