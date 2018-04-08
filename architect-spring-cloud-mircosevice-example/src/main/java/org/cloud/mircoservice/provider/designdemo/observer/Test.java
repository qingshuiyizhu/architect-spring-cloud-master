package org.cloud.mircoservice.provider.designdemo.observer;

public class Test {

	public static void main(String[] args) {

		XiaoMei xiao_mei = new XiaoMei();//被观察者
		XiaoMing xiao_ming = new XiaoMing();//观察者
		XiaoWang xiao_wang = new XiaoWang();
		// 小明和小王在小美那里注册了一下
		xiao_mei.addStudent(xiao_ming);
		xiao_mei.addStudent(xiao_wang);
		// 小美向小明和小王发送通知
		xiao_mei.notifyPerson();

	}

}
