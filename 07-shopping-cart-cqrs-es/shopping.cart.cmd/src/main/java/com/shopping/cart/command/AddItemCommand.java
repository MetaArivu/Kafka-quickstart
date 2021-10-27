package com.shopping.cart.command;

public class AddItemCommand extends CartCommand {

	public AddItemCommand(String customerId, String itemId, int qty) {
		super(customerId, itemId, qty);
	}
}
