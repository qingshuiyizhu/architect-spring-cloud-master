package org.cloud.mircoservice.provider.designdemo.decorator;

/*
 *装饰者模式Decorator
 *实现方式：
 *a) 抽象的被装饰角色(所有的角色都要直接或者间接的实现本角色)
 *b) 具体的被装饰角色，实现或继承a(被功能扩展的角色)
 *c) 装饰角色，实现或继承a(本类有对a的引用，所有的具体装饰角色都需要继承这个角色)
 *d） 多个具体修饰角色 ，继承c（对被装饰角色的功能扩展，可以任意搭配使用）
 *举个栗子，我想吃三明治，首先我需要一根大大的香肠，我喜欢吃奶油，在香肠上面加一点奶油，
 * 再放一点蔬菜，最后再用两片面包加一下，很丰盛的一顿午饭，营养又健康，
 * 那我们应该怎么来写代码呢？ 
 */
public class Test {
	public static void main(String[] args) {
		Food food = new Bread(new Vegetable(new Cream(new Food("香肠"))));
		System.out.println(food.make());
		/*
		 *一层一层封装，我们从里往外看：最里面我new了一个香肠，在香肠的外面我包裹了一层奶油，在奶油的外面我又加了一层蔬菜，
		 *最外面我放的是面包，是不是很形象，这个设计模式简直跟现实生活中一摸一样，看懂了吗？ 
		 */
	}
}
