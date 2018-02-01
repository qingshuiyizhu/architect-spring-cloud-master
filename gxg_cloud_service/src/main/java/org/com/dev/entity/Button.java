package org.com.dev.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

@Table(name = "BUTTON")
@Entity
public class Button {
	
	private Integer id;
	private String name;
	//按钮类型id
	private Integer tid;
	//按钮类型名称
	private String tname;
	//按钮内容包含客户端id，程序id, 参数值
	private String content;
    
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
	
	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}
 
	@Transient  //表示该属性并非一个到数据库表的字段的映射,ORM框架将忽略该属性.
	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

 
	@Lob   
    @Basic(fetch = FetchType.LAZY)   
    @Type(type="text")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Button() {
		super();
	}

	public Button(Integer id, String name, Integer tid,String tname) {
		super();
		this.id = id;
		this.name = name;
		this.tid = tid;
		this.tname = tname;
	}
	
	
}
