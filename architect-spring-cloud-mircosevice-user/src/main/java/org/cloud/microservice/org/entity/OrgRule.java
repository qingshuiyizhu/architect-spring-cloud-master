package org.cloud.microservice.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_org_rule")
public class OrgRule {
//默认管理类型，表示直线型的管理，某个组织职能管理它下面的所有子组织
	public static int DEFAULT_TYPE = 0;
	// 可以管理所有的组织机构中的信息
	public static int ALL_TYPE = 0;
	//自定义的管理类型，如果是该管理类型，需要到managerOrg这个字
	//段中获取可以管理的所有的组织
	public static int DEF_TYPE = 2;
	//如果组织的管理类型属于-1，表示不具备管理功能
	public static int NO_TYPE = -1;
	
	private Long id;
	//组织id
	private Long orgId;
	 
	/*
	 * 具体的管理组织,当管理类型为DEF_TYPE时，就从该字段中找到所有的可以管理的组织
	 * 2|3|4
	 * 这里存储的是管理组织的根组织
	 */
	
	private String managerOrg;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "org_id")
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	@Column(name = "manager_org")
	public String getManagerOrg() {
		return managerOrg;
	}

	public void setManagerOrg(String managerOrg) {
		this.managerOrg = managerOrg;
	}
	
	
	
	
}
