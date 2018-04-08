package org.cloud.mircoservice.bank;

public class NumberMachine {
	// 普通客户
	private NumberManager commonManager = new NumberManager();
	// 快速客户
	private NumberManager expressManager = new NumberManager();
	// vip客户
	private NumberManager vipManager = new NumberManager();

	public NumberManager getCommonManager() {
		return commonManager;
	}

	public NumberManager getExpressManager() {
		return expressManager;
	}

	public NumberManager getVipManager() {
		return vipManager;
	}

	// 单例模式
	private NumberMachine() {
	}

	public static NumberMachine getInstance() {
		return instance;
	}

	private static NumberMachine instance = new NumberMachine();

}
