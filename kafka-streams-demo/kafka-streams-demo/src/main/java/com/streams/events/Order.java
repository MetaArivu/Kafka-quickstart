package com.streams.events;

public class Order {

	private String id;
	private Double total;

	public Order() {

	}

	public Order(String orderId, Double total) {
		super();
		this.id = orderId;
		this.total = total;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return this.id + "|" + this.total;
	}
}
