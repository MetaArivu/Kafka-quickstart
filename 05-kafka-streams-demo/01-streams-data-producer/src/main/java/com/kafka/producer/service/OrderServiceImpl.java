package com.kafka.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.kafka.producer.model.OrderDetails;
import com.kafka.producer.util.OrderDataBuilder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

	@Autowired
	private KafkaTemplate<String, OrderDetails> kafkaTemplate;
	
	@Autowired
	private OrderDataBuilder orderDataBuilder;
 
	/**
	 * This will publish event to USER-EVENT topic with key, this will make sure next event goes to same key goes to same partition
	 * 
	 */
	
	@Override
	public OrderDetails publishOrder() {
		OrderDetails event = orderDataBuilder.orderData();
		kafkaTemplate.send("order-details", event.getId(), event);
		log.info("Event Published with key = {} on Topic={}", event.getId(),"order-details");
		return event;
	}
	
	@Override
	public OrderDetails publishInvalidOrder() {
		OrderDetails event = orderDataBuilder.invalidOrderData();
		kafkaTemplate.send("order-details", event.getId(), event);
		log.info("Event Published with key = {} on Topic={}", event.getId(),"order-details");
		return event;
	}
	
	@Override
	public OrderDetails withoutAddressOrderData(String id) {
		OrderDetails event = orderDataBuilder.withoutAddressOrderData(id);
		kafkaTemplate.send("order-details", event.getId(), event);
		log.info("Event Published with key = {} on Topic={}", event.getId(),"order-details");
		return event;
	}
	
	@Override
	public OrderDetails publishInternationalOrder() {
		OrderDetails event = orderDataBuilder.internationalOrderData();
		kafkaTemplate.send("order-details", event.getId(), event);
		log.info("Event Published with key = {} on Topic={}", event.getId(),"order-details");
		return event;
	}
	
	@Override
	public OrderDetails publishOrderWithCustomerId(String id) {
		OrderDetails event = orderDataBuilder.orderDataWithCustomerId(id);
		
		kafkaTemplate.send("order-details", event.getId(), event);
		log.info("Event Published with key = {} on Topic={}", event.getId(),"order-details");
		return event;
	}
	
 
	

}
