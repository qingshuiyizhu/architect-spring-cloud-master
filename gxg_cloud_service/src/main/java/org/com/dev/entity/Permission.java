package org.com.dev.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "PERMISSION")
public class Permission {
private Integer id;
private String name;
private UserRole role;
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
@JoinColumn(name = "ROLE_ID")
@ManyToOne(fetch = FetchType.LAZY)
public UserRole getRole() {
	return role;
}
public void setRole(UserRole role) {
	this.role = role;
}

}
