package com.streams;

public class Order {

	private String orderId;
	private Double total;

	public Order() {

	}

	public Order(String orderId, Double total) {
		super();
		this.orderId = orderId;
		this.total = total;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return this.orderId + "|" + this.total;
	}
}
