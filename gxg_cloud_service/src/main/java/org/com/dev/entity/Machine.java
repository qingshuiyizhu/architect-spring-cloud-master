package org.com.dev.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

 
@Table(name = "MACHINE")
@Entity
public class Machine {
	// 设备id
	private Integer id;
 	// 设备名
	private String name;
	// IP地址
	private String ip;
	// MAC地址
	private String mac;
	private Integer port;
	// 设备组
	private Integer mgid;
	private String mgname;
 	// 设备审核状态
	private Integer state;
	// 设备心跳状态
	private Integer heartbeat;
	// 创建时间
	private Date createTime;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

    public Integer getMgid() {
		return mgid;
	}

	public void setMgid(Integer mgid) {
		this.mgid = mgid;
	}
 
	@Transient
	public String getMgname() {
		return mgname;
	}

	public void setMgname(String mgname) {
		this.mgname = mgname;
	}

	@Column(name = "state", columnDefinition = "int default 0")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "heartbeat", columnDefinition = "int default 0")
	public Integer getHeartbeat() {
		return heartbeat;
	}

	public void setHeartbeat(Integer heartbeat) {
		this.heartbeat = heartbeat;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Machine() {
		super();
	}
 
	public Machine(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Machine(Integer id, String name, Integer mgid, String mgname) {
		super();
		this.id = id;
		this.name = name;
		this.mgid = mgid;
		this.mgname = mgname;
	}

	public Machine(Integer id, String name, String ip, String mac, Integer port, String mgname, Integer state,
			Integer heartbeat, Date createTime) {
		super();
		this.id = id;
		this.name = name;
		this.ip = ip;
		this.mac = mac;
		this.port = port;
		this.mgname = mgname;
		this.state = state;
		this.heartbeat = heartbeat;
		this.createTime = createTime;
	}
	
}
