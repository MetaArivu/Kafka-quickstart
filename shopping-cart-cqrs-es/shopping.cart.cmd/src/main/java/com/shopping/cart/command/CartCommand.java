package com.shopping.cart.command;

public abstract class CartCommand {

	private String customerId;
	private String itemId;
	private int qty;

	public CartCommand(String customerId, String itemId, int qty) {
		super();
		this.customerId = customerId;
		this.itemId = itemId;
		this.qty = qty;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getItemId() {
		return itemId;
	}

	public int getQty() {
		return qty;
	}

}
