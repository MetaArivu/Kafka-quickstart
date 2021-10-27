package com.kafka.producer.service;

import com.kafka.producer.model.UserCreatedEvent;

public interface EventProducerService {

	public UserCreatedEvent simpleEventPublisher(UserCreatedEvent userCreatedEvent);
	
	public UserCreatedEvent eventPublisherWithKey(UserCreatedEvent userCreatedEvent);
	
	public UserCreatedEvent eventPublisherWithCallBack(UserCreatedEvent userCreatedEvent);

	public UserCreatedEvent eventPublisherWithHeader(UserCreatedEvent userCreatedEvent);

	public UserCreatedEvent synchronousEventPublisher(UserCreatedEvent userCreatedEvent);

}
