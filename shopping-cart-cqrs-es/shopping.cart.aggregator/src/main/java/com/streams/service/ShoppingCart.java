package com.streams.service;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

	private String customerId;
	private List<LineItems> lineItems = new ArrayList<LineItems>();
	
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public List<LineItems> getLineItems() {
		return lineItems;
	}
	public void setLineItems(List<LineItems> lineItems) {
		this.lineItems = lineItems;
	}
	
	public void addLineItem(LineItems lineItem) {
		lineItems.add(lineItem);
	}
	@Override
	public String toString() {
		return "ShoppingCart [customerId=" + customerId + ", lineItems=" + lineItems + "]";
	}
	
	
	
}
