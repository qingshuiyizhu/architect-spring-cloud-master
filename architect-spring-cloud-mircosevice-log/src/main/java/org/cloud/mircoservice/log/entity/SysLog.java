package org.cloud.mircoservice.log.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.cloud.mircoservice.common.entity.UuIdEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
@Table(name = "SYSLOG")
@Entity
public class SysLog extends UuIdEntity implements Serializable{
 	 
	private static final long serialVersionUID = 1L;
	 
 	/**用户id*/
    private Integer userId;
    /**模块名称*/
    private String moduleName;
    /**操作*/
    private String operate;
    /**时间*/
    private Date time;
    /**类名*/
    private String className;
    /**方法名*/
    private String methodName;
    /**传入参数*/
    private String params;
    /**操作ip*/
    private String ip;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
 	
 	
 	
 	
 	
 	
}
