package org.cloud.mircoservice.provider.webservice.axis;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

import java.rmi.RemoteException;

public class Axis2Document
{
    public static void main(String[] args) throws RemoteException {
       
    	testDocument();
    }

    public static void test() throws AxisFault {
        try {
            String url = "http://localhost:8080/soap/user";
            Options options = new Options();
            EndpointReference targetEPR = new EndpointReference(url);
            options.setTo(targetEPR);
            options.setAction("http://webservice.dev.com.org/" + "getFiles");//需要加上这条语句
            ServiceClient sender = new ServiceClient();
            sender.setOptions(options);
            OMFactory fac = OMAbstractFactory.getOMFactory();
            String tns = "http://webservice.dev.com.org/";
            OMNamespace omNs = fac.createOMNamespace(tns, "");
            OMElement method = fac.createOMElement("getFiles", omNs);
             OMElement symbol = fac.createOMElement("mac", omNs);
            symbol.addChild(fac.createOMText(symbol, "ffff"));
             method.addChild(symbol);
            method.build();
            OMElement result = sender.sendReceive(method);
            System.out.println(result);
        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        }
    }
    
    public static void testDocument() {  
        try {  
          // String url = "http://localhost:8080/axis2ServerDemo/services/StockQuoteService";  
          String url = "http://localhost:8080/soap/user?wsdl";  
      
          Options options = new Options();  
          // 指定调用WebService的URL  
          EndpointReference targetEPR = new EndpointReference(url);  
          options.setTo(targetEPR);  
          // options.setAction("urn:getPrice");  
      
          ServiceClient sender = new ServiceClient();  
          sender.setOptions(options);  
            
            
          OMFactory fac = OMAbstractFactory.getOMFactory();  
          String tns = "http://webservice.dev.com.org/";  
          // 命名空间，有时命名空间不增加没事，不过最好加上，因为有时有事，你懂的  
          OMNamespace omNs = fac.createOMNamespace(tns, "");  
      
          OMElement method = fac.createOMElement("getFiles", omNs);  
          OMElement symbol = fac.createOMElement("ff", omNs);  
          symbol.setText("1");  
          symbol.addChild(fac.createOMText(symbol, "Axis2 Echo String "));  
          method.addChild(symbol);  
          method.build();  
            
          OMElement result = sender.sendReceive(method);  
      
          System.out.println(result);  
      
        } catch (AxisFault axisFault) {  
          axisFault.printStackTrace();  
        }  
      }  
      
     /** 
      * 为SOAP Header构造验证信息， 
      * 如果你的服务端是没有验证的，那么你不用在Header中增加验证信息 
      * 
      * @param serviceClient 
      * @param tns 命名空间 
      * @param user 
      * @param passwrod 
      */  
      public void addValidation(ServiceClient serviceClient, String tns , String user, String passwrod) {  
        OMFactory fac = OMAbstractFactory.getOMFactory();  
        OMNamespace omNs = fac.createOMNamespace(tns, "nsl");  
        OMElement header = fac.createOMElement("AuthenticationToken", omNs);  
        OMElement ome_user = fac.createOMElement("Username", omNs);  
        OMElement ome_pass = fac.createOMElement("Password", omNs);  
          
        ome_user.setText(user);  
        ome_pass.setText(passwrod);  
          
        header.addChild(ome_user);  
        header.addChild(ome_pass);  
      
        serviceClient.addHeader(header);  
      }  
      
}
