package org.com.dev.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name = "REQUEST")
@Entity
public class Request {
	// 设备MAC地址 machine_MAC
	// 设备名称 machine_name
	// 程序路径 appPath
	// 程序参数 param
	// 创建时间 crate_time
	// 执行状态 state

	private Integer id;
	private String machine_MAC;
	private String machine_name;
	private int orderType;
	private String appPath;
	private String param;
	private Integer state;
	private Date createTime;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMachine_MAC() {
		return machine_MAC;
	}

	public void setMachine_MAC(String machine_MAC) {
		this.machine_MAC = machine_MAC;
	}

	public String getMachine_name() {
		return machine_name;
	}

	public void setMachine_name(String machine_name) {
		this.machine_name = machine_name;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public String getAppPath() {
		return appPath;
	}

	public void setAppPath(String appPath) {
		this.appPath = appPath;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	@Column(name = "state", columnDefinition = "int default 0")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
