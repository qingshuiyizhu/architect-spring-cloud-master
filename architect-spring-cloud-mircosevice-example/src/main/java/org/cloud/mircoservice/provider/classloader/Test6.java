package org.cloud.mircoservice.provider.classloader;
/*
 * 只有当程序访问的静态变量或静态方法确实在当前类或当前接口中定义时，
 * 才可以认为是对类或接口的主动使用。
 */

class Parent3{
	static int a = 3;
	static {
		System.out.println("Parent3 static block");
	}
	static void doSomething() {
		System.out.println("do something");
	 	}
}

class Child3 extends Parent3{
	static {
		System.out.println("Child3 static block");
	}
}
 
public class Test6 {
public static void main(String[] args) {
	System.out.println(Child3.a);
	Child3.doSomething();
}
}
