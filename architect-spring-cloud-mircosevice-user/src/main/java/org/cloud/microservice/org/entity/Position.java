package org.cloud.microservice.org.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 岗位对象，用来确定某个人员所属的岗位 存储的就是岗位的名称 校长，副校长，处长，副处长，科长，普通员工
 * 
 * @author LINGHUI
 *
 */
@Entity
@Table(name = "t_position")
public class Position {
	// 岗位的标识
	private Long id;
	// 岗位的名称
	private String name;
	// 岗位的sn
	private String sn;
	// 岗位是否具备管理功能
	private int manager;

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

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public int getManager() {
		return manager;
	}

	public void setManager(int manager) {
		this.manager = manager;
	}

}
