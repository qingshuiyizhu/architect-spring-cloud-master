package org.cloud.mircoservice.provider.webservice.axis;
/*
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

public class AxisClient {
	public static void main(String[] args) throws RemoteException, ServiceException {
		try {
			System.out.println("开始。。");
			String endpoint = "http://localhost:8080/soap/user";
		   //步骤1 构建 org.apache.axis.client.Service 对象
			Service service = new Service();
			// 步骤2：通过org.apache.axis.client.Service对象创建一个Call,需要强转为 org.apache.axis.client.Call类型
			Call call = (Call) service.createCall();
			 // 步骤3：设置目标地址，即需要访问的webservice地址
			call.setTargetEndpointAddress(new URL(endpoint));
			  // 步骤4：设置调用的命名空间和方法名
			call.setOperationName(new QName("http://webservice.dev.com.org/", "getFiles"));// WSDL里面描述的接口名称

			// 设置SOAPAction .net发布的服务需要使用
			// call.setUseSOAPAction(true);
			// 可以在wsdl中找个这个地址
			// call.setSOAPActionURI("http://webservice.dev.com.org/getFiles");

			 // 步骤5： 设置参数名
            call.addParameter("mac", // 参数名
                    XMLType.XSD_STRING, // 参数类型:String
                    ParameterMode.IN); // 参数模式：'IN' or 'OUT'
            call.addParameter("mac", // 参数名
                    XMLType.XSD_STRING, // 参数类型:String
                    ParameterMode.IN); // 参数模式：'IN' or 'OUT'
            // 步骤6：设置返回值类型
		 	call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
																			// String
			// call.setTimeout(100000);//设置超时

			// 传入参数 mac = 测试
			String temp = "测试";
			String result = (String) call.invoke(new Object[] { temp,temp });
			// 给方法传递参数，并且调用方法
			System.out.println(result);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}
}
*/
