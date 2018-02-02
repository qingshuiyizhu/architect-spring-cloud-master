package org.cloud.mircoservice.provider.webservice;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

// WebService接口
 
@WebService(name = "cxfWebService",   // 暴露服务名称
		   targetNamespace = "http://webservice.mircoservice.cloud.org/")   // 命名空间,一般是接口的包名倒序
		

public interface MyWebService {
   //获取同步列表 传入mac地址
 	@WebMethod
    @WebResult(name = "String", targetNamespace = "http://webservice.mircoservice.cloud.org/")  
	public String getName(@WebParam(name = "id")String id);
 
 	@WebMethod
	@WebResult(name = "String", targetNamespace = "") 
	public String getNumber(@WebParam(name = "number1") String number1,@WebParam(name = "number2") String number2);
 		
	
}