package org.com.dev.webservice;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.BindingOperationInfo;

public class App {
 /*   public static void main(String[] args) throws Exception {  
        String url = "http://localhost:8080/webservice/soap?wsdl";  
        String method = "getFiles";  
        Object[] parameters = new Object[]{"eee"};  
        System.out.println(invokeRemoteMethod(url, method, parameters)[0]);  
    }  */
      
    public static Object[] invokeRemoteMethod(String url, String operation, Object[] parameters){  
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();  
        if (!url.endsWith("wsdl")) {  
            url += "?wsdl";  
        }  
        org.apache.cxf.endpoint.Client client = dcf.createClient(url);  
        //处理webService接口和实现类namespace不同的情况，CXF动态客户端在处理此问题时，会报No operation was found with the name的异常  
        Endpoint endpoint = client.getEndpoint();  
        QName opName = new QName(endpoint.getService().getName().getNamespaceURI(),operation);  
        BindingInfo bindingInfo= endpoint.getEndpointInfo().getBinding();  
        if(bindingInfo.getOperation(opName) == null){  
            for(BindingOperationInfo operationInfo : bindingInfo.getOperations()){  
                if(operation.equals(operationInfo.getName().getLocalPart())){  
                    opName = operationInfo.getName();  
                    break;  
                }  
            }  
        }  
        Object[] res = null;  
        try {  
            res = client.invoke(opName, parameters);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return res;  
    }     
}
