package com.shopping.cart.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ItemRemovedEvent implements CartEvent {

	private String customerId;
	private String itemId;
	private int qty;
	private EventType eventType;

	@Override
	public String toString() {
		return "ItemRemovedEvent [customerId=" + customerId + ", itemId=" + itemId + ", qty=" + qty + ", eventType="
				+ eventType + "]";
	}

}
