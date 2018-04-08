package org.cloud.mircoservice.provider.designdemo.observer;

import java.util.ArrayList;
import java.util.List;

//角色抽象类（提供对观察者的添加，删除和通知功能）。这里就不抽象了
//角色具体类，实现a，维护一个c的集合（对角色抽象类的实现）
public class XiaoMei {

	List<Student> list = new ArrayList<Student>();

	public void addStudent(Student student) {
		list.add(student);
	}

	// 遍历list，把自己的通知发送给所有向自己注册过的人
	public void notifyPerson() {
		for (Student student : list) {
			student.getMessage("老师进教室了!");
		}
	}
}
