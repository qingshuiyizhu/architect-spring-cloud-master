package org.cloud.mircoservice.provider.designdemo.decorator;

//面包类
public class Bread extends Food {
	private Food basic_food;

	public Bread(Food basic_food) {
		this.basic_food = basic_food;
	}

	public String make() {
		return basic_food.make() + "+面包";
	}
}
//奶油类
class Cream extends Food {
	private Food basic_food;

	public Cream(Food basic_food) {
		this.basic_food = basic_food;
	}
	public String make() {
		return basic_food.make() + "+奶油";
	}
}
//蔬菜类
class Vegetable extends Food {
		private Food basic_food;
		public Vegetable(Food basic_food) {
			this.basic_food = basic_food;
		}
		public String make() {
			return basic_food.make() + "+蔬菜";
		}
	}
