package org.com.dev.entity;

import java.io.File;

//转发
public class FileTransfer {
	// 转发的类型 文件转发1 指令转发2
	private Integer type;
	// 接受的mac地址数组
	private String[] macs;
	// 指令的参数
	private String program;
	//指令的路徑
	private String path;
	//传输的文件
	private File file;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String[] getMacs() {
		return macs;
	}

	public void setMacs(String[] macs) {
		this.macs = macs;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
