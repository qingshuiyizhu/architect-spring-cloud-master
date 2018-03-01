package org.cloud.microservice.user.entity;

import java.io.Serializable;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.cloud.mircoservice.common.entity.IdEntity;
/**
 * 
 * @author LINGHUI 用户角色映射表
 */
public class RoleUser extends IdEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private User User;
	private Role Role;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	public User getUser() {
		return this.User;
	}

	public void setUser(User User) {
		this.User = User;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "roleid")
	public Role getRole() {
		return this.Role;
	}

	public void setRole(Role Role) {
		this.Role = Role;
	}
}
