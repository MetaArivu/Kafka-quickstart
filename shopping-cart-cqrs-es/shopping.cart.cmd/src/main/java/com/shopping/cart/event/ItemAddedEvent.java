package com.shopping.cart.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ItemAddedEvent implements CartEvent {

	private String customerId;
	private String itemId;
	private int qty;
	private EventType eventType;

	@Override
	public String toString() {
		return eventType + "|" + customerId + "|" + itemId + "|" + qty;
	}

}
