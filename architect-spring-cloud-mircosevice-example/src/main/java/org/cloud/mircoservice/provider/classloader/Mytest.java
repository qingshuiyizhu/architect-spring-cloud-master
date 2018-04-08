package org.cloud.mircoservice.provider.classloader;
class Singleton{
	//加载 
	//初始化
	
	private static Singleton singleton = new Singleton(); 
	public static int counter1;
	public static int counter2 = 0;
	
	private Singleton() {
		counter1++;
		counter2++;
	}
	public static Singleton getInstance() {
		return singleton;
	}
}




public class Mytest {
public static void main(String[] args) {
	Singleton singleton = Singleton.getInstance();
	System.out.println("counter1="+singleton.counter1);
	System.out.println("counter2="+singleton.counter2);
	
}
 
	
	
	
	
	
	
}


