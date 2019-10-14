package com.jf.shop.designmodel.factorymethod.pizzastore.order;


import com.jf.shop.designmodel.factorymethod.pizzastore.pizza.LDCheesePizza;
import com.jf.shop.designmodel.factorymethod.pizzastore.pizza.LDPepperPizza;
import com.jf.shop.designmodel.factorymethod.pizzastore.pizza.Pizza;

public class LDOrderPizza extends OrderPizza {

	
	@Override
    Pizza createPizza(String orderType) {
	
		Pizza pizza = null;
		if(orderType.equals("cheese")) {
			pizza = new LDCheesePizza();
		} else if (orderType.equals("pepper")) {
			pizza = new LDPepperPizza();
		}
		// TODO Auto-generated method stub
		return pizza;
	}

}
