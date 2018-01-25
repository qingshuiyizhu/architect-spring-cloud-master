package org.cloud.microservice.user.entity;

import java.io.Serializable;

import org.cloud.mircoservice.common.entity.IdEntity;

public class User extends IdEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	// 用户名
	private String userName;
	// 用户密码
	private String password;
	// 身份证号码
	private String idcar;
	// 电子邮箱
	private String email;
	// 手机
	private String mobliePhone;
	// 办公室电话
	private String officePhone;
	// 签名文件
	private String signatureFile;
	// 肖像
	private String portrait;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdcar() {
		return idcar;
	}

	public void setIdcar(String idcar) {
		this.idcar = idcar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobliePhone() {
		return mobliePhone;
	}

	public void setMobliePhone(String mobliePhone) {
		this.mobliePhone = mobliePhone;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getSignatureFile() {
		return signatureFile;
	}

	public void setSignatureFile(String signatureFile) {
		this.signatureFile = signatureFile;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

}
