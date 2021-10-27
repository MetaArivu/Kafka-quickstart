package com.kafka.producer.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderDetails {

	private String id;

	private String customerId;

	private Date date;

	private List<LineItems> lineItems;

	private Address addrress;

	private Double total;

	public OrderDetails(String customerId, Date date, List<LineItems> lineItems, Address addrress) {
		super();
		this.id = UUID.randomUUID().toString();
		this.customerId = customerId;
		this.date = date;
		this.lineItems = lineItems;
		this.addrress = addrress;
		this.calculateTotal();
	}

	private void calculateTotal() {
		if (lineItems != null) {
			total = lineItems.parallelStream()
					.map(lt -> lt.getTotal())
					.reduce(0d, (a, b) -> a + b);
		}
	}

	public String getId() {
		return id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public Date getDate() {
		return date;
	}

	public List<LineItems> getLineItems() {
		return lineItems;
	}

	public Address getAddrress() {
		return addrress;
	}

	public Double getTotal() {
		return total;
	}
	
	
}
