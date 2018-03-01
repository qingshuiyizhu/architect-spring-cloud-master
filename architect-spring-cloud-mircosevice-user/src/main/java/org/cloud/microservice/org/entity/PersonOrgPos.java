package org.cloud.microservice.org.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 人员组织岗位对应关系表，这张表里面存储了人员和组织和岗位的对应关系
 * 
 * @author LINGHUI
 *
 */
public class PersonOrgPos {
	// 关系标识
	private Long id;
	// 人员id，这里也可以存储实体类，但是不建议
	private Long personId;
	// 组织的id
	private Long orgId;
	// 岗位的id
	private Long posId;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "person_id")
	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	@Column(name = "org_id")
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
 
	@Column(name = "pos_id")
	public Long getPosId() {
		return posId;
	}

	public void setPosId(Long posId) {
		this.posId = posId;
	}

}
