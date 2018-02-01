package org.com.dev.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User {
	private Integer id;
	private String name;
	private String password;
	private Date create_time;
	private Date alter_time;
	private Integer rid;
	private String rname;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*@JoinColumn(name = "ROLE_ID")
	@ManyToOne(fetch = FetchType.LAZY)
	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}*/
	
 	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getAlter_time() {
		return alter_time;
	}

	public void setAlter_time(Date alter_time) {
		this.alter_time = alter_time;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public User(Integer id, String name, String password, Date create_time, Date alter_time, Integer rid,
			String rname) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.create_time = create_time;
		this.alter_time = alter_time;
		this.rid = rid;
		this.rname = rname;
	}

	public User() {
		super();
		 
	}
	
	

}
