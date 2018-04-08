package org.cloud.mircoservice.bank;

import java.util.Random;
import java.util.concurrent.Executors;

/**
 * 没有把VIP窗口和快速窗口做成子类，是因为实际业务中的普通窗口可以随时被设置为VIP窗口和快速窗口。
 */
public class ServiceWindow {
	//默认是普通窗口
	private CustomerType type = CustomerType.COMMON;
	private int windowId = 1;

	public void setType(CustomerType type) {
		this.type = type;
	}

	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}

	public void start() {
		Executors.newSingleThreadExecutor().execute(new Runnable() {
			@Override
			public void run() {
				// 下面这种写法的运行效率低，最好是把while放在case下面
				while (true) {
					switch (type) {
					case COMMON:
						commonService();
						break;
					case EXPRESS:
						expressService();
						break;
					case VIP:
						vipService();
						break;
					}

				}
			}

			private void vipService() {
				Integer serviceNumber = NumberMachine.getInstance().getVipManager().fetchServiceNumber();
				String windowName = "第" + windowId + "号" + type + "窗口";	
				System.out.println(windowName + "开始获取VIP任务!");			
				if(serviceNumber !=null){
					System.out.println(windowName + "开始为第" + serviceNumber + "号VIP客户服务");			
					int maxRandom = Constants.MAX_SERVICE_TIME - Constants.MIN_SERVICE_TIME;
					int serviceTime = new Random().nextInt(maxRandom)+1 + Constants.MIN_SERVICE_TIME;
					try {
						Thread.sleep(serviceTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}		
					System.out.println(windowName + "完成为第" + serviceNumber + "号VIP客户服务，总共耗时" + serviceTime/1000 + "秒");		
				}else{
					System.out.println(windowName + "没有取到VIP任务！");				
					commonService();
				}	
				
			}

			private void expressService() {
				Integer serviceNumber = NumberMachine.getInstance().getExpressManager().fetchServiceNumber();
				String windowName = "第" + windowId + "号" + type + "窗口";	
				System.out.println(windowName + "开始获取快速任务!");		
				if(serviceNumber !=null){
					System.out.println(windowName + "开始为第" + serviceNumber + "号快速客户服务");			
					int serviceTime = Constants.MIN_SERVICE_TIME;
					try {
						Thread.sleep(serviceTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}		
					System.out.println(windowName + "完成为第" + serviceNumber + "号快速客户服务，总共耗时" + serviceTime/1000 + "秒");		
				}else{
					System.out.println(windowName + "没有取到快速任务！");				
					commonService();
				}
				
			}

			private void commonService() {
				String windowName = "第" + windowId + "号" + type + "窗口";
				System.out.println(windowName + "正在获取普通任务！");
				// 获取普通任务
				Integer number = NumberMachine.getInstance().getCommonManager().fetchServiceNumber();
				if (number != null) {
					// long beginTime = System.currentTimeMillis();
					int maxRandom = Constants.MAX_SERVICE_TIME - Constants.MIN_SERVICE_TIME;
					// 服务所需时间
					int serviceTime = new Random().nextInt(maxRandom) + 1 + Constants.MIN_SERVICE_TIME;

					try {
						Thread.sleep(serviceTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(windowName + "完成为第" + number + "号普通客户服务，总共耗时" + serviceTime / 1000 + "秒");

				} else {
					System.out.println(windowName + "没有取到普通任务，正在空闲一秒");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		});
	}

}
