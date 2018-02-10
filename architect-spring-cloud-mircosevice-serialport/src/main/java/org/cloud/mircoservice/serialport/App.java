package org.cloud.mircoservice.serialport;

//串口通信控制器入口
public class App {
	private static SerialComUtil serialComUtil = new SerialComUtil();
 public static void main(String[] args) {
		//传入参数为json字符串
	 	String[] args1 = args;
		if (args1.length > 0) {
			String str = args1[0];
			String[] strs = str.split(":");
			for (int i = 0, len = strs.length; i < len; i++) {
				System.out.println("第"+i+"个参数为："+strs[i].toString());
			}
			String model = "";
			String connector = strs[0];
			String action = strs[1];
			String baudrate = "9600";
			serialComUtil.excuteSingleProjector(model, connector, action, baudrate);
		}
	}
}
