package org.cloud.mircoservice.provider.winsystem;

import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * 
 * @ClassName: getLocalHost
 * @Description: TODO(获取本机的MAC地址 IP地址 计算机名称)
 * @author LINGHUI
 * @date 2017年7月28日
 *
 */
public class getLocalHost {
	public void getLocal() {

		// 获取本机的MAC地址 IP
		try {
			InetAddress ia = InetAddress.getLocalHost();// 获取本地IP对象
			String localname = ia.getHostName();
			System.out.println("本机名称是：" + localname);
			System.out.println("本机的ip是 ：" + ia.getHostAddress());
			System.out.println("MAC ......... " + getMACAddress(ia));
		} catch (Exception e) {
		}

	}

	// 获取MAC地址的方法
	private static String getMACAddress(InetAddress ia) throws Exception {
		// 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();

		// 下面代码是把mac地址拼装成String
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < mac.length; i++) {
			if (i != 0) {
				sb.append("-");
			}
			// mac[i] & 0xFF 是为了把byte转化为正整数
			String s = Integer.toHexString(mac[i] & 0xFF);
			sb.append(s.length() == 1 ? 0 + s : s);
		}
		// 把字符串所有小写字母改为大写成为正规的mac地址并返回
		return sb.toString().toUpperCase();
	}

}
