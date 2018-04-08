package org.cloud.mircoservice.provider.designdemo;

/*
 单例模式
 a）	将被实现的类的构造方法设计成private的。
 b）添加此类的静态成员变量，并为其实例化。
 c）	在被实现的类中提供公共的CreateInstance·函数，返回实例化的此类，就是b中的静态成员变量。

 */
public class Singleton {

	// 添加此类的静态成员变量，并为其实例化。
	private static Singleton singleton = null;
	private Singleton() {

	}
	public static Singleton getInstance() {
		if (singleton == null) {
			singleton = new Singleton();
		}
		return singleton;
	}
}
