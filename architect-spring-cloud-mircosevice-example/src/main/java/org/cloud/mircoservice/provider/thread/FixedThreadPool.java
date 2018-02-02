package org.cloud.mircoservice.provider.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {

	public static void main(String[] args) {

		// 创建一个可重用固定线程数的线程池
		ExecutorService pool = Executors.newFixedThreadPool(3);
		// 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
		Thread t1 = null;
		int i=1;
		while (true) {
			
			// 将线程放入池中进行执行
			t1 = new MyThread(i);
	        t1.start();
	       
			i++;
			pool.execute(t1);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	}

	class MyThread extends Thread {
		private int i;
		
		public int getI() {
			return i;
		}
		public void setI(int i) {
			this.i = i;
		}
		
		public MyThread() {
			super();
			 
		}
		public MyThread(Runnable target, String name) {
			super(target, name);
			// TODO Auto-generated constructor stub
		}
		 
		public MyThread(int i) {
			super();
			this.i = i;
		}
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + "正在执行。。。"+this.i);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
			 
				e.printStackTrace();
			}
		}
	}
 