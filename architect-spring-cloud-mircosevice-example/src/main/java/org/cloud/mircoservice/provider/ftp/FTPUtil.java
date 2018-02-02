package org.cloud.mircoservice.provider.ftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPListParseEngine;
import org.apache.commons.net.ftp.FTPReply;

/*
 * 
    * @ClassName: FTPUtil  FTP 工具类
    * @Description:  
    * 1.ftp的连接与关闭
    * 2.ftp的文件列表获取
    * 3.ftp服务器文件的删除
    * 4.ftp文件上传
    * 5.ftp文件下载
    * 
    * 
    * @author LINGHUI
    * @date 2017年7月3日
    *
 */
public class FTPUtil {

	private FTPClient ftpClient = null;
	// FTP服务器的IP地址
	private String ftpIP;
	// FTP服务器的端口号
	private int port;
	// 登录FTP服务器的用户名
	private String userName;
	// 登录FTP服务器的用户密码
	private String userPassword;
	// 设置编码格式
	private String encode = "GBK";
	// ftp 文件名编码 FTP协议里面，规定文件名编码为iso-8859-1，所以目录名或文件名需要转码
	// private String fncode ="iso-8859-1";

	// 5.ftp文件下载
	/**
	 * @param remotePath
	 *            FTP服务器上的相对路径
	 * @param fileName
	 *            要下载的文件名
	 * @param localPath
	 *            下载后保存到本地的路径
	 * @return boolean
	 */
	public boolean downFile(String remotePath, String fileName, String localPath) {
		boolean sign = false;
		try {
			ftpClient.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
			FTPFile[] fs = ftpClient.listFiles();
			for (FTPFile ff : fs) {
				System.out.println("ftp的文件名:" + ff.getName());
				if (ff.getName().equals(fileName)) {
					File localFile = new File(localPath + File.separator + ff.getName());
					OutputStream is = new FileOutputStream(localFile);
					ftpClient.retrieveFile(new String(ff.getName().getBytes("GBK"), "iso-8859-1"), is);
					is.flush();
					is.close();
					System.out.println(ff.getName() + "下载完成！");
					sign = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sign;
	}

	// 4.ftp文件上传
 	/**
	 * @param path
	 *            FTP服务器保存目录"/"或者""为ftp文件夹根目录。其他路径相对于服务器根目录
	 * @param filename
	 *            上传到FTP服务器上的文件名
	 * @param input
	 *            输入流
	 * @return 成功返回true，否则返回false
	 */
	public boolean uploadFile(String path, String filename, InputStream input) {
		boolean success = false;
		try {
			ftpClient.changeWorkingDirectory(path);
			ftpClient.storeFile(filename, input);
			input.close();
			ftpClient.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return success;
	}

	// 3.ftp服务器文件的删除
	/**
	 * 3.1删除FTP上的文件
	 *
	 * @param ftpDirAndFileName
	 */
	public boolean deleteFile(String ftpDirAndFileName) {
		boolean flag = false;
		if (!ftpClient.isConnected()) {
			return flag;
		}
		try {
			flag = ftpClient.deleteFile(new String(ftpDirAndFileName.getBytes("GBK"), "iso-8859-1"));
		} catch (IOException e) {
			this.closeFTP();
			e.printStackTrace();
		}
		this.closeFTP();
		return flag;
	}

	// 3.2 迭代删除FTP服务器上的文件夹目录 (多层子文件夹不删除可能出现问题)
	public boolean deleteIterate(String ftpPath) throws IOException {
		FTPFile[] files = ftpClient.listFiles(new String(ftpPath.getBytes("GBK"), "iso-8859-1"));
		boolean flag = false;
		for (FTPFile f : files) {
			String path = ftpPath + File.separator + f.getName();
			System.out.println(path);
			if (f.isFile()) {
				// 是文件就删除文件
				ftpClient.deleteFile(new String(path.getBytes("GBK"), "iso-8859-1"));
			} else if (f.isDirectory()) {
				deleteIterate(new String(path.getBytes("GBK"), "iso-8859-1"));
			}
		}
		// 每次删除文件夹以后就去查看该文件夹下面是否还有文件，没有就删除该空文件夹
		FTPFile[] files2 = ftpClient.listFiles(new String(ftpPath.getBytes("GBK"), "iso-8859-1"));
		if (files2.length == 0) {
			flag = ftpClient.removeDirectory(new String(ftpPath.getBytes("GBK"), "iso-8859-1"));
		} else {
			flag = false;
		}
		return flag;
	}

	/*
	 * public List<String> getFileList1(String path) throws Exception {
	 * List<String> fileLists = new ArrayList<String>(); // 获得指定目录下所有文件名
	 * FTPFile[] ftpFiles = null; try { ftpFiles = ftpClient.listFiles(path); }
	 * catch (IOException e) { e.printStackTrace(); } for (int i = 0; ftpFiles
	 * != null && i < ftpFiles.length; i++) { FTPFile file = ftpFiles[i]; if
	 * (file.isFile()) { System.out.println("文件夹下面的文件====="+file.getName());
	 * fileLists.add(file.getName()); }else if(file.isDirectory()){
	 * System.out.println("文件夹名称为====="+file.getName()); List<String> childLists
	 * = getFileList1(path + file.getName()+"/"); for(String childFileName :
	 * childLists){ fileLists.add(childFileName); String fileType =
	 * childFileName.substring(childFileName.lastIndexOf(".")+1,childFileName.
	 * length()); System.out.println("文件类型为："+fileType); FtpUtils ftp = new
	 * FtpUtils(); if(fileType.equals("txt")){
	 * System.out.println("文件名为："+childFileName); String content = ""; content =
	 * ftp.readFile(path + file.getName()+"/"+childFileName);
	 * System.out.println("文件内容为："+content); } } } } return fileLists; }
	 */

	// 循环遍历获取FTP服务器的指定的文件夹下的所有文件和文件名
	public List<String> getFileListAll(String ftpPath) throws IOException {

		List<String> fileList = new ArrayList<String>();
		// 将远程的ftp路径中的斜杠统一
		char[] chars = ftpPath.toCharArray();
		StringBuffer sbStr = new StringBuffer(256);
		for (char c : chars) {
			if ('\\' == c || '/' == c) {
				sbStr.append(File.separator);
			} else {
				sbStr.append(c);
			}
		}

		ftpPath = sbStr.toString();
		System.out.println(ftpPath);
		// String filePath = ftpPath.substring(0,
		// ftpPath.lastIndexOf(File.separator));
		// String fileName =
		// ftpPath.substring(ftpPath.lastIndexOf(File.separator) + 1);
		// System.out.println(ftpPath);

		// 获取指定文件下的所有文件名
		FTPFile[] files = ftpClient.listFiles(new String(ftpPath.getBytes("GBK"), "iso-8859-1"));
		boolean flag = false;
		for (FTPFile f : files) {
			String path = ftpPath + File.separator + f.getName();
			if (f.isFile()) {
				// 是文件 就添加到list
				fileList.add(path);
				// System.out.println(path);
			} else if (f.isDirectory()) {
				System.out.println(path);
				getFileListAll(new String(path.getBytes("GBK"), "iso-8859-1"));
			}
		}
		return fileList;
	}

	// 2.ftp的文件列表获取 获取FTP服务器[指定路径]下的文件列表 包括文件夹和文件 只需要文件时用isFile循环遍历判断
	public FTPFile[] getFileList(String remotePath) {
		FTPFile[] list;
		try {
			// ftpClient.setControlEncoding("GBK");
			list = ftpClient.listFiles(new String(remotePath.getBytes("GBK"), "iso-8859-1"));
			return list;
		} catch (Exception e) {
			System.out.println("获取FTP服务器[" + remotePath + "]下的文件列表失败");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取FTP服务器[指定路径]下的文件列表
	 * 
	 * @param remotePath
	 * @return
	 */

	public FTPFile[] getFtpServerFileList(String remotePath) {
		FTPFile[] list;
		try {
			FTPListParseEngine engine = ftpClient
					.initiateListParsing(new String(remotePath.getBytes("GBK"), "iso-8859-1"));
			list = engine.getNext(25);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 获取FTP服务器指定文件夹下目录
	public FTPFile[] getFileListDirectories(String remotePath) {
		FTPFile[] list;
		try {
			// list = ftpClient.listDirectories(); //不带参数为FTP服务器根目录
			list = ftpClient.listDirectories(new String(remotePath.getBytes("GBK"), "iso-8859-1"));
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 1.开启ftp的连接
	public boolean openFTP() {
		// 判断ftp连接是否开启
		if (ftpClient != null && ftpClient.isConnected()) {
			return true;
		}
		try {
			// 建立连接
			ftpClient = new FTPClient();
			// 建立ftp连接 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftpClient.connect(this.ftpIP, this.port);
			// 进行ftp登录
			ftpClient.login(this.userName, this.userPassword);
			// 设置文件编码格式
			ftpClient.setControlEncoding(encode);
			
			/*要获取文件名之后，用name=new String(name.getBytes("UTF-8"),"GBK");这样的方式，当然并不一定就是这两种编码，顺序也不一定.
			 * FileZilla Server默认使用utf-8，而我们的操作系统一般都是中文的，默认字符集是gbk，因此就不行了。有建议使用FileZilla Server的某个特殊版或者打补丁，最简便的方法是给ftp服务器发送一个命令“opts utf8 off”,就一切ok了。
			 */
		 	//ftpClient.doCommand("opts", "utf8 off");
			// SYST_NT ---对应windows系统
			FTPClientConfig ftpClientConfig = new FTPClientConfig(FTPClientConfig.SYST_NT);
			// 系统编码为中文
			ftpClientConfig.setServerLanguageCode("zh");
			// 设置文件 二进制传输格式
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			// 设置文件流传输
			ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
			// 设置缓冲
			ftpClient.setBufferSize(2048);
			// 切换工作路径为根目录
			ftpClient.changeWorkingDirectory("/");
			// 检测连接是否成功
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				this.closeFTP();
				System.err.println("FTP server refused connection!");
				System.exit(1);
			}
			System.out.println(
					"Open FTP server Success:" + this.ftpIP + ";port:" + this.port + ";userName:" + this.userName);

			return true;
		} catch (Exception e) {
			this.closeFTP();
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 2.关闭ftp连接
	 */
	public void closeFTP() {
		if (ftpClient != null && ftpClient.isConnected()) {
			try {
				ftpClient.logout();
				ftpClient.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Close FTP Server Success :" + this.ftpIP + "; port: " + this.port);
		}
	}

	// 初始化构造方法
	public FTPUtil(String ftpIP, int port, String userName, String userPassword) {
		super();
		this.ftpIP = ftpIP;
		this.port = port;
		this.userName = userName;
		this.userPassword = userPassword;
	}

	public FTPUtil() {
		super();
	}

	public FTPClient getFtpClient() {
		return ftpClient;
	}

	public void setFtpClient(FTPClient ftpClient) {
		this.ftpClient = ftpClient;
	}
}
