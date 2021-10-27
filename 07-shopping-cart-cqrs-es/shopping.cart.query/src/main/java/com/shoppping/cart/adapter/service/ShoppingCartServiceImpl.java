package com.shoppping.cart.adapter.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.shoppping.cart.adapter.entity.ShoppingCart;
import com.shoppping.cart.adapter.repo.ShoppingCartRepo;
import com.shoppping.cart.domainlayer.service.ShoppingCartService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Override
	@KafkaListener(topics = { "shopping-cart-aggregate" })
	public void consumeShoppingCartEvent(ConsumerRecord<String, String> event) {
		log.info("Shopping Cart Event Received key={}, offset={}, partition={}", event.key(), event.offset(), event.partition());
		log.info("Shopping Cart Message={}", event.value());
		ShoppingCart shoppingCart = ShoppingCart.parse(event.value());
		ShoppingCartRepo.instance().add(shoppingCart);
		log.info("ShoppingCart="+this.findByCustomerId(shoppingCart.getCustomerId()));
	}
	
	@Override
	public ShoppingCart findByCustomerId(String customerId) {
		return ShoppingCartRepo.instance().get(customerId);
	}

}
