package com.shopping.cart.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ClearCartEvent implements CartEvent {

	private String customerId;
	private EventType eventType;

	@Override
	public String toString() {
		return eventType + "|" + customerId + "|";
	}

}
