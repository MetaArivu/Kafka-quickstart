package com.kafka.producer.model;

public class LineItems {

	private String itemId;
	private String item;
	private int qty;
	private double price;
	private double total;
	
	public LineItems(String itemId, String item, int qty, double price) {
		super();
		this.itemId = itemId;
		this.item = item;
		this.qty = qty;
		this.price = price;
		this.total = qty * price;
	}

	public String getItemId() {
		return itemId;
	}

	public String getItem() {
		return item;
	}

	public int getQty() {
		return qty;
	}

	public double getPrice() {
		return price;
	}

	public double getTotal() {
		return total;
	}
	
	
	
	
	
}
