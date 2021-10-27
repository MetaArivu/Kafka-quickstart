package com.shopping.cart.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Slf4j
public class CartEvent {

	private String customerId;
	private String itemId;
	private String itemName;
	private double price;
	private int qty;
	private EventType eventType;

	public static CartEvent parse(String _event) {
		try {
			return new ObjectMapper().readValue(_event, CartEvent.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			log.error("Error while parsing CustomerCartEvent, Exception=" + e.getMessage());
			return null;
		}
	}

	@Override
	public String toString() {
		return customerId+"|"+itemId+"|"+itemName+"|"+price+"|"+qty+"|"+eventType;
	}
}
