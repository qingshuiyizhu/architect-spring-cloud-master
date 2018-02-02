package org.cloud.mircoservice.provider.webservice.axis;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

public class Axis2RPCClient {
	/*
	 * 采用RPC通信 的webservice client
	 */

	/**
	 * 方法一： 应用rpc的方式调用 这种方式就等于远程调用， 即通过url定位告诉远程服务器，告知方法名称，参数等， 调用远程服务，得到结果。 使用
	 * org.apache.axis2.rpc.client.RPCServiceClient类调用WebService
	 * 
	 * 【注】：
	 * 
	 * 如果被调用的WebService方法有返回值 应使用 invokeBlocking 方法 该方法有三个参数
	 * 第一个参数的类型是QName对象，表示要调用的方法名； 第二个参数表示要调用的WebService方法的参数值，参数类型为Object[]；
	 * 当方法没有参数时，invokeBlocking方法的第二个参数值不能是null，而要使用new Object[]{}。
	 * 第三个参数表示WebService方法的 返回值类型的Class对象，参数类型为Class[]。
	 * 
	 * 
	 * 如果被调用的WebService方法没有返回值 应使用 invokeRobust 方法
	 * 该方法只有两个参数，它们的含义与invokeBlocking方法的前两个参数的含义相同。
	 * 
	 * 在创建QName对象时，QName类的构造方法的第一个参数表示WSDL文件的命名空间名， 也就是
	 * <wsdl:definitions>元素的targetNamespace属性值。
	 * 
	 */

	// wsdl-->soap:address location
	public static String address = "http://localhost:8080/soap/user";
	public static String address1 = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";

	public static void main(String[] args) throws IOException {

		testUser();
		testArray();
		testTwoArray();
		testUser();

	}

	@SuppressWarnings("rawtypes")
	// method 调用的方法
	// params 参数
	// classes 返回值类型
	public static Object[] invoke(String method, Object[] params, Class[] classes) throws AxisFault {
		// 使用RPC方式调用WebService
		RPCServiceClient client = new RPCServiceClient();
		Options option = client.getOptions();

		// 指定调用的URL
		EndpointReference reference = new EndpointReference(address1);
		option.setTo(reference);
		// JAX-WS规范不需要SoapAction，但是.NET需要
		// options.setAction("目标的TargetNameSpace"+"调用的方法名");
		option.setAction("http://WebXml.com.cn/" + "getWeatherbyCityName");// 需要加上这条语句
		/*
		 * 设置要调用的方法 http://ws.apache.org/axis2 为默认的（无package的情况）命名空间， 如果有包名，则为
		 * http://axis2.webservice.elgin.com ,包名倒过来即可 method为方法名称
		 * 
		 */
		QName qname = new QName("http://WebXml.com.cn/", method);

		// 调用远程方法,并指定方法参数以及返回值类型
		Object[] result = client.invokeBlocking(qname, params, classes);

		return result;

	}

	public static void testUpload() throws IOException {
		String dir = System.getProperty("user.dir");
		File file = new File(dir + "/WebContent" + "/hello.jsp");
		FileInputStream fis = new FileInputStream(file);
		int len = (int) file.length();
		byte[] b = new byte[len];
		int read = fis.read(b);
		fis.close();
		Object[] result = invoke("upload", new Object[] { b, read }, new Class[] { String.class });
		System.out.println(result[0]);
	}

	public static void testArray() throws AxisFault {
		Object[] result = invoke("getArray", new Object[] { 5 }, new Class[] { int[].class });
		int[] arr = (int[]) result[0];
		for (int i : arr) {
			System.out.println(i);
		}
	}

	public static void testTwoArray() throws AxisFault {
		Object[] result = invoke("getTwoArray", new Object[] {}, new Class[] { String[][].class });
		String[][] arr = (String[][]) result[0];
		for (String[] strings : arr) {
			for (String str : strings) {
				System.out.println(str);
			}
		}
	}

	public static void testUser() throws AxisFault {
	//	Object[] result = invoke("getUser", new Object[] {}, new Class[] { User.class });
	//	User user = (User) result[0];
   //   System.out.println(user.toString());
	}

}
