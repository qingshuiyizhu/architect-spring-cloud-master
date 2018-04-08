package org.cloud.mircoservice.provider.classloader;
/*
 * 程序中对子类的“主动使用”会导致父类被初始化；但对父类的“主动使用”
 * 并不会导致子类初始化（不可能说生成一个Object类的对象就导致系统中所有的子类都会被初始化）
 * 
 * 
 * 
 */
class Parent2 {
	static int a = 3;
	static {
		System.out.println("Parent static block");
	}
}

class Child2 extends Parent2{
	static int b = 4;
	static {
		System.out.println("Child2 static block");
	}
}
public class Test5 {
static {
	System.out.println("Test5 static block");
}
	public static void main(String[] args) {
	 	Parent2 parent;
		System.out.println("-----------------");
		parent = new Parent2();
		System.out.println(Parent2.a);
		System.out.println(Child2.b);
	}
	
}
