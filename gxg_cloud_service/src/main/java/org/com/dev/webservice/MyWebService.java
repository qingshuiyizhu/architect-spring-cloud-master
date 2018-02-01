package org.com.dev.webservice;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
/**
 * WebService接口
 * 
 *  
 *
 */
@WebService(name = "cxfWebService", // 暴露服务名称
		targetNamespace = "http://webservice.dev.com.org/"// 命名空间,一般是接口的包名倒序
		)

public interface MyWebService {
 	
    //获取同步列表 传入mac地址
 	@WebMethod
    @WebResult(name = "String", targetNamespace = "")  
	public String getFiles( String mac);

    //已经删除文件名称-从数据库中删除
 	@WebMethod
	@WebResult(name = "String", targetNamespace = "") 
	public String delFile( String mac,String path);
  
 	//同步完成率 
 	/*参数有：客户端的MAC地址
 	 *        文件路径，文件名称 同步完成率
 	 * 
 	 * 
 	 */
	@WebMethod
	@WebResult(name = "String", targetNamespace = "") 
	public String downFile( String mac, String remotePath, String fileName,Integer finish);
	
   
 		
}