package com.kafka.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.kafka.producer.model.OrderEvent;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EventPublisherWithKey {

	@Autowired
	private KafkaTemplate<String, OrderEvent> kafkaTemplate;
	
	
	public OrderEvent pushlishEvent(OrderEvent event) {
		kafkaTemplate.send("orders-event", event.getId(), event);
		log.info("Event Published with key = {} on Topic={}", event.getId(),"orders-event");
		return event;
	}
}
