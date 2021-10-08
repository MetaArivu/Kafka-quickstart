package com.kafka.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kafka.producer.avro.model.UserCreatedEvent;

@Component
public class EventProducerService {

	@Autowired
	private SimpleEventPublisher simpleEventPublisher;
	
	public UserCreatedEvent simpleEventPublisher(UserCreatedEvent userCreatedEvent) {
		simpleEventPublisher.pushlishEvent(userCreatedEvent);
		return userCreatedEvent;
	}
	 

}
