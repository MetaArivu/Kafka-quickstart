package com.shopping.cart.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductEvent {

	private String productId;
	
	private String productName;
	
	private double price;
	
	private int stockQty;
	
	public ProductEvent() {
		
	}

	public ProductEvent(String productId, String productName, double price, int stockQty) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.stockQty = stockQty;
	}

	public String getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public double getPrice() {
		return price;
	}

	public int getStockQty() {
		return stockQty;
	}
	
	public static ProductEvent parse(String _event) {
		try {
			return new ObjectMapper().readValue(_event, ProductEvent.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			log.error("Error while parsing ProductEvent, Exception=" + e.getMessage());
			return null;
		}
	}

	@Override
	public String toString() {
		return "ProductEvent [productId=" + productId + ", productName=" + productName + ", price=" + price
				+ ", stockQty=" + stockQty + "]";
	}
	
	
}
