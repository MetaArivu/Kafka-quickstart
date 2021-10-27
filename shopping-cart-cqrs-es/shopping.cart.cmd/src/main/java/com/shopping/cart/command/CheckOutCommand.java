package com.shopping.cart.command;

public class CheckOutCommand {

	private String customerId;

	public CheckOutCommand() {

	}

	public CheckOutCommand(String customerId) {
		super();
		this.customerId = customerId;
	}

	public String getCustomerId() {
		return customerId;
	}

}
