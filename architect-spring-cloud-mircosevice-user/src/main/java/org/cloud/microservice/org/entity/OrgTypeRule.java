package org.cloud.microservice.org.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author LINGHUI 组织机构类型的规则表，用来确定组织之间的关系
 * 
 */

@Entity
@Table(name = "t_org_type_rule")
public class OrgTypeRule {
	// 规则标识
	private Long id;
	// 规则的父id
	private Long pid;
	// 规则的子id
	private Long cid;
	// 两者之间的数量，如果为-1表示可以有无限个
	private Integer num;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
