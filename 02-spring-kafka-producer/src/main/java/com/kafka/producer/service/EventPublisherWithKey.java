package com.kafka.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.kafka.producer.model.UserCreatedEvent;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EventPublisherWithKey {

	@Autowired
	private KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;
	
	
	public UserCreatedEvent pushlishEvent(UserCreatedEvent userCreatedEvent) {
		kafkaTemplate.send("user-event", userCreatedEvent.getId(), userCreatedEvent);
		log.info("2: Event Published with key = {}", userCreatedEvent.getId());
		return userCreatedEvent;
	}
}
