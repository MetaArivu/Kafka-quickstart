package com.kafka.producer.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.kafka.producer.avro.model.UserCreatedEvent;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SimpleEventPublisher {

	@Autowired
	private KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;
	
	
	public UserCreatedEvent pushlishEvent(UserCreatedEvent userCreatedEvent) {
		userCreatedEvent.setId(UUID.randomUUID().toString()); // Once data is saved, it will generate ID.

		kafkaTemplate.send("user-event", userCreatedEvent);

		log.info("1: Event published");
		return userCreatedEvent;
	}
}
