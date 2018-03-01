package org.cloud.microservice.user.entity;

import java.io.Serializable;
import java.util.Date;

import org.cloud.mircoservice.common.entity.IdEntity;

/**
 * 
 * @author LINGHUI 用户角色表
 */
public class Role extends IdEntity implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	// 角色编码 如：admin
	private String roleCode;
	// 角色名称 如：管理员
	private String roleName;

	// 创建时间
	private Date createDate;
	// 创建者编码
	private String createBy;
	// 创建者名称
	private String createName;
	// 更新时间
	private String updateDate;
	// 更新者编码
	private String updateBy;
	// 更新者名称
	private String updateName;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

}
