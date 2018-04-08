package org.cloud.mircoservice.provider.classloader;
class FinalTest{
	public static final int x = 6/3;
	//x在编译时就能计算出来，编译时的常量
	static {
		System.out.println("FinalTest static block");
	}
}




public class Test2 {
public static void main(String[] args) {
	System.out.println(FinalTest.x);
}
	
}
