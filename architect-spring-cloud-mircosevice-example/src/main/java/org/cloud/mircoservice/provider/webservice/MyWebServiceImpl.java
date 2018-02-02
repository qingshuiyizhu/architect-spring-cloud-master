package org.cloud.mircoservice.provider.webservice;

import javax.jws.WebService;

import org.springframework.stereotype.Component;

@WebService(serviceName = "cxfWebService",       // 与接口中指定的name一致
		    targetNamespace = "http://webservice.mircoservice.cloud.org/",     // 与接口中的命名空间一致,一般是接口的包名倒
		     endpointInterface = "org.cloud.mircoservice.provider.webservice.MyWebService")     // 接口地址

@Component
public class MyWebServiceImpl implements MyWebService {

	@Override
	public String getName(String id) {
		 
		return "张三";
	}

	@Override
	public String getNumber(String number1, String number2) {
		 
		return number1+"add"+number2;
	}

	 


}
