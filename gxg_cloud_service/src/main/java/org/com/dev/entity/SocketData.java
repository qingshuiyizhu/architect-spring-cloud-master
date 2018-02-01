package org.com.dev.entity;

import java.io.Serializable;

public class SocketData implements Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ip;
	private String mac;
	private String heart;
	//指令类型
	private Integer orderType;
    private String appPath;
	private String porgram;
	//文件转发
	private FileTransfer fileTransfer;
	
    public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getHeart() {
		return heart;
	}

	public void setHeart(String heart) {
		this.heart = heart;
	}

	public String getAppPath() {
		return appPath;
	}

	public void setAppPath(String appPath) {
		this.appPath = appPath;
	}

	 
	public String getPorgram() {
		return porgram;
	}

	public void setPorgram(String porgram) {
		this.porgram = porgram;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public FileTransfer getFileTransfer() {
		return fileTransfer;
	}

	public void setFileTransfer(FileTransfer fileTransfer) {
		this.fileTransfer = fileTransfer;
	}

	@Override
	public String toString() {
		return "SocketData [ip=" + ip + ", mac=" + mac + ", heart=" + heart + ", orderType=" + orderType + ", appPath="
				+ appPath + ", porgram=" + porgram + "]";
	}

	 
	
}
