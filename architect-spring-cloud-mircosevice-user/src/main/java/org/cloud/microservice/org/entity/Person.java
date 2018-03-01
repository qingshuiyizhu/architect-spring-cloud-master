package org.cloud.microservice.org.entity;
/*
 *人员对象,对于人员而言，某些项目中，可能Person里面会有一些非常特殊的属性，并不能够在人员对象中
 * 一一展示，如学校系统：教师和学生要存储的信息完全不一致，此时就创建两个关联类来和Person进行关联
 * 然后根据情况来确定是否有必要提供添加人员的功能，如果都是使用其他对象关联，如果所有的人员要么是教师
 * 要么是学生，此时可以提供相应的教师和学生的添加功能，在添加学生和教师的时候直接添加Person对象
 * 所以此处就把的公共属性抽取出来
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_person")
public class Person {
	private Long id;
	private String name;
	// 身份证号
	private String idcard;
	// 性别
	private int sex;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}

}
