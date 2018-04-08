package org.cloud.mircoservice.provider.designdemo.observer;
/*
     观察者接口
 */
public interface Student {
   //观察者通过这个接口方法接收被观察者发送过来的消息
	void getMessage(String s);
}
