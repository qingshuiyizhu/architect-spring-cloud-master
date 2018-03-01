package org.cloud.microservice.org.entity;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 
 * @author LINGHUI 组织对象，该表可以生成完整的组织树 根据组织类型来具体存储实际中存在的组织
 * 
 */

public class Org {
	// 组织机构的id
	private Long id;
	// 组织机构的名称
	private String name;
	// 组织机构所属类型的id，此处不要使用ManyToOne（一对多）
	private String typeId;
	// 组织机构所属名称，冗余
	private String typeName;
	// 组织机构的排序号
	private Integer orderNum;
	// 组织机构的父组织
	private Org parent;
	//管理类型
	private int managerType; 
	 
	// 组织机构的地址
	private String address;
	// 组织机构的电话
	private String phone;
	/*
	 *扩展属性，用于针对某些特殊的组织存储相应的信息
	 */
	private String att1;
	private String att2;
	private String att3;
	private String att4;
	private String att5;
	private String att6;

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

	@Column(name = "tid")
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	@Column(name = "tname")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "order_num")
	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	@ManyToOne
	@JoinColumn(name = "pid")
	public Org getParent() {
		return parent;
	}

	public void setParent(Org parent) {
		this.parent = parent;
	}

	@Column(name = "manager_type")
	public int getManagerType() {
		return managerType;
	}

	public void setManagerType(int managerType) {
		this.managerType = managerType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAtt1() {
		return att1;
	}

	public void setAtt1(String att1) {
		this.att1 = att1;
	}

	public String getAtt2() {
		return att2;
	}

	public void setAtt2(String att2) {
		this.att2 = att2;
	}

	public String getAtt3() {
		return att3;
	}

	public void setAtt3(String att3) {
		this.att3 = att3;
	}

	public String getAtt4() {
		return att4;
	}

	public void setAtt4(String att4) {
		this.att4 = att4;
	}

	public String getAtt5() {
		return att5;
	}

	public void setAtt5(String att5) {
		this.att5 = att5;
	}

	public String getAtt6() {
		return att6;
	}

	public void setAtt6(String att6) {
		this.att6 = att6;
	}

}
