package com.shopping.cart.command;

public class RemoveItemCommand extends CartCommand {

	public RemoveItemCommand(String customerId, String itemId) {
		super(customerId, itemId, 0);
	}
}
