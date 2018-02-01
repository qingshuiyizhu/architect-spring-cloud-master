package org.com.dev.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "ClientFile")
@Entity
public class ClientFile {
	@GeneratedValue
	@Id
	private Integer id;
	// 客户端id
	private Integer cid;
	// 父节点id -设备id
	private Integer pId;
	// 文件名称 
	private String name;
	// 文件路径
	private String path;
	// 同步状态
	private Integer schedule;
	// 是不是被刪除 删除1 未删除0
	private Integer isdel;
	private long fileSize;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getpId() {
		return pId;
	}
	 
	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	@Column(name = "schedule", columnDefinition = "int default 0")
	public Integer getSchedule() {
		return schedule;
	}

	public void setSchedule(Integer schedule) {
		this.schedule = schedule;
	}
	@Column(name = "isdel", columnDefinition = "int default 0")
	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	
	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public ClientFile() {
		super();
		 
	}

	public ClientFile(Integer id, Integer pId, String name) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
	}


}
