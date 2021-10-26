package com.shopping.cart.adapter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.shopping.cart.command.AddItemCommand;
import com.shopping.cart.command.RemoveItemCommand;
import com.shopping.cart.domainlayer.service.CartService;
import com.shopping.cart.event.CartEvent;
import com.shopping.cart.event.EventType;
import com.shopping.cart.event.ItemAddedEvent;
import com.shopping.cart.event.ItemRemovedEvent;
import com.shopping.cart.exception.ServiceException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

	@Autowired
	private KafkaTemplate<String, CartEvent> cartKafkaTemplate;
	
	@Override
	public boolean addItem(AddItemCommand addItem) throws ServiceException {
		ItemAddedEvent event = ItemAddedEvent.builder()
								.customerId(addItem.getCustomerId())
								.itemId(addItem.getItemId())
								.qty(addItem.getQty())
								.eventType(EventType.ADD_ITEM)
								.build();
		log.info("Item Added Event Published, Event={}",event);
		cartKafkaTemplate.send("cart-events", event.getCustomerId(), event);
		return true;
	}

	@Override
	public boolean removeItem(RemoveItemCommand removeItem) throws ServiceException {
		ItemRemovedEvent event = ItemRemovedEvent.builder()
									.customerId(removeItem.getCustomerId())
									.itemId(removeItem.getItemId())
									.qty(0)
									.eventType(EventType.REMOVE_ITEM)
									.build();
		log.info("Item Removed Event Published, Event={}",event);
		cartKafkaTemplate.send("cart-events", event.getCustomerId(), event);
		return true;
	}

}
