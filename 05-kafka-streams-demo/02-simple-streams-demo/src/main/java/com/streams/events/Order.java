package com.streams.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

	public static Order parse(String str) {
		try {
			return new ObjectMapper().readValue(str, Order.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String toJson() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "Error=" + e.getMessage();
		}
	}

	@Override
	public String toString() {
		return toJson();
	}
}
