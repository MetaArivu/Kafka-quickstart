package com.streams.events;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class OrderDetails {

	private String id;

	private String customerId;

	private Date date;

	private List<LineItems> lineItems;

	private Address addrress;

	private Double total;
 
	
}
