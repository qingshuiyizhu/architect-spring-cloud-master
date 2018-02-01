package org.com.dev.entity;

public class ZTreeNode {
	private Integer id;
	private Integer pId;
	//文件名称（含文件大小,显示在文件树上）
	private String name;
	//文件名称
	private String fileName;
	//文件路径
	private String path;
	//文件大小
	private long fileSize;
	private boolean isParent;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

	public ZTreeNode() {
		super();
	}

	 
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public ZTreeNode(Integer id, Integer pId,String fileName, String name,String path, long fileSize ,boolean isParent) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.fileName = fileName;
		this.path = path;
		this.isParent = isParent;
		this.fileSize = fileSize;
	}
	

}
