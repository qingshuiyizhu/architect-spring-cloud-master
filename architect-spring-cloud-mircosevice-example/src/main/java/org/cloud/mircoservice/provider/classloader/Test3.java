package org.cloud.mircoservice.provider.classloader;

import java.util.Random;

class FinalTest2{
	//在运行时Random()才知道自己的值
	public static final int x = new Random().nextInt(100);
	static {
		System.out.println("FinalTest2 static block");
 	}
 
}
                                             
public class Test3 {
public static void main(String[] args) {
	System.out.println(FinalTest2.x);
}
}
