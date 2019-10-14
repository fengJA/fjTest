package com.jf.shop.designmodel.factorymethod.pizzastore.order;


import com.jf.shop.designmodel.factorymethod.pizzastore.pizza.BJCheesePizza;
import com.jf.shop.designmodel.factorymethod.pizzastore.pizza.BJPepperPizza;
import com.jf.shop.designmodel.factorymethod.pizzastore.pizza.Pizza;

public class BJOrderPizza extends OrderPizza {

	
	@Override
	Pizza createPizza(String orderType) {
	
		Pizza pizza = null;
		if(orderType.equals("cheese")) {
			pizza = new BJCheesePizza();
		} else if (orderType.equals("pepper")) {
			pizza = new BJPepperPizza();
		}
		// TODO Auto-generated method stub
		return pizza;
	}

}
