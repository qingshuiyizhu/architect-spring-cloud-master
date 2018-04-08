package org.cloud.mircoservice.bank;

import java.util.ArrayList;
import java.util.List;

//号码队列
public class NumberManager {
	private int lastNumber = 1;
	private List<Integer> queueNumber = new ArrayList<Integer>();

	// 队列中号码+1
	public synchronized Integer generateNewNumber() {
		queueNumber.add(lastNumber);
		return lastNumber++;
	}

	// 从队列中去除号码
	public synchronized Integer fetchServiceNumber() {
		// return lastNumber;
		Integer number = null;
		if (queueNumber.size() > 0) {
			number = queueNumber.remove(0);
		}
		return number;
		// List每remove掉一个元素以后，后面的元素都会向前移动
	}

}
