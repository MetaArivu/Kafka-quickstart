package com.shoppping.cart.domainlayer.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface ShoppingCartEventConsumerService {

	public void consumeShoppingCartEvent(ConsumerRecord<String, String> event);
}
