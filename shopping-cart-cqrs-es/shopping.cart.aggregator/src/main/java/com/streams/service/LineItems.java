package com.streams.service;

public class LineItems {

	private String itemId;
	private int qty;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "LineItems [itemId=" + itemId + ", qty=" + qty + "]";
	}

	
}
