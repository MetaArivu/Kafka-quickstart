package com.shopping.cart.event;

public class LineItems {

	private String itemId;
	private int qty;

	public LineItems() {
		
	}
	public LineItems(String _itemId, int qty) {
		this.itemId = _itemId;
		this.qty = qty;
	}

	public String getItemId() {
		return itemId;
	}

	public int getQty() {
		return qty;
	}

	@Override
	public String toString() {
		return "LineItems [itemId=" + itemId + ", qty=" + qty + "]";
	}

}
