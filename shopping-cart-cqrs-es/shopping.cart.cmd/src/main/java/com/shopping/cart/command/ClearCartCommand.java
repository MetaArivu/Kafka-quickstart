package com.shopping.cart.command;

public class ClearCartCommand {

	private String customerId;

	public ClearCartCommand() {
		
	}
	
	public ClearCartCommand(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerId() {
		return customerId;
	}

}
