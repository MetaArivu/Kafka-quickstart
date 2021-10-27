package com.shoppping.cart.adapter.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.shoppping.cart.domainlayer.service.ShoppingCartEventConsumerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ShoppingCartEventConsumerServiceImpl implements ShoppingCartEventConsumerService {

	@Override
	@KafkaListener(topics = { "shopping-cart-aggregate" })
	public void consumeShoppingCartEvent(ConsumerRecord<String, String> event) {
		log.info("Shopping Cart Event Received key={}, offset={}, partition={}", event.key(), event.offset(), event.partition());
		log.info("Shopping Cart Message={}", event.value());
	}

}
