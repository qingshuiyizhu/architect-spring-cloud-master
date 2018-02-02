package org.cloud.mircoservice.provider.demo;

public class SwitchDemo {
	/*
	 * default就是如果没有符合的case就执行它,default并不是必须的. case后的语句可以不用大括号.
	 * switch语句的判断条件可以接受int,byte,char,short,不能接受其他类型.
	 * 一旦case匹配,就会顺序执行后面的程序代码,而不管后面的case是否匹配,直到遇见break,利用这一特性可以让好几个case执行统一语句.
	 */
	// switch(A),括号中A的取值只能是整型或者可以转换为整型的数值类型，比如byte、short、int、char、还有枚举；
	//需要强调的是：long和String类型是不能作用在switch语句上的。
	// case后面必须跟常量，必须要常量 变量声明为final即可。
	private final static int NUM1 = 1;
	private final static int NUM2 = 2;

	public static void main(String[] args) {

		int i = 3;
		switch (i) {

		case NUM1:
			System.out.println(1);
			break;
		case NUM2:
			System.out.println(2);
			break;
		default:
			System.out.println("default");
			break;
		}
	}

}
