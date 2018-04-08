package org.cloud.mircoservice.provider.designdemo.decorator;
/*
 * 我们需要写一个Food类，让其他所有食物都来继承这个类
 */
public class Food {
	
private String food_name;

public Food() {
}

public Food(String food_name) {
	this.food_name = food_name;
}

public String make() {
	return food_name;
}

}
