package com.shoppping.cart.adapter.repo;

import java.util.Collection;
import java.util.HashMap;

import com.shoppping.cart.adapter.entity.ShoppingCart;

public class ShoppingCartRepo {

	private static final ShoppingCartRepo instance = new ShoppingCartRepo();

	private HashMap<String, ShoppingCart> data = new HashMap<String, ShoppingCart>();

	private ShoppingCartRepo() {

	}

	public static ShoppingCartRepo instance() {
		return instance;
	}

	public ShoppingCart add(ShoppingCart shoppingCart) {
		return data.put(shoppingCart.getCustomerId(), shoppingCart);
	}

	public ShoppingCart get(String customerId) {
		return data.get(customerId);
	}
	
	public Collection<ShoppingCart> getAll() {
		return data.values();
	}

}
