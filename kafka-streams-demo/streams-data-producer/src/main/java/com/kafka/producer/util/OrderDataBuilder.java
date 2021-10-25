package com.kafka.producer.util;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.kafka.producer.model.Address;
import com.kafka.producer.model.LineItems;
import com.kafka.producer.model.OrderDetails;

@Component
public class OrderDataBuilder {

	public OrderDetails orderData() {
		OrderDetails event = new OrderDetails(UUID.randomUUID().toString(), new Date(), lineItems(), address());
		return event;
	}
	
	private List<LineItems> lineItems(){
		return Arrays.asList(
				new LineItems("item-1", "IPhone 13", 1, 130000d),
				new LineItems("item-1", "MacBook Pro 15", 1, 230000d),
				new LineItems("item-1", "Airpod", 1, 24000d));
	}
	
	
	private Address address() {
		return new Address("Home", "E205, East Street Park", "MG Road", "Pune", "MH", "India", "411028");
	}
}