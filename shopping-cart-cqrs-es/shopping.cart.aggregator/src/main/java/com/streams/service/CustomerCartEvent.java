package com.streams.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.cart.event.EventType;

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
public class CustomerCartEvent {

	private String customerId;
	private String itemId;
	private int qty;
	private EventType eventType;

	public static CustomerCartEvent parse(String _event) {
		try {
			return new ObjectMapper().readValue(_event, CustomerCartEvent.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			log.error("Error while parsing CustomerCartEvent, Exception=" + e.getMessage());
			return null;
		}
	}

	@Override
	public String toString() {
		return "CustomerCartEvent [customerId=" + customerId + ", itemId=" + itemId + ", qty=" + qty + ", eventType="
				+ eventType + "]";
	}
}
