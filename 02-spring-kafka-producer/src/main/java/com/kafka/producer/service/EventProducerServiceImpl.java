package com.kafka.producer.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import com.kafka.producer.model.UserCreatedEvent;

@Service
@RequestScope
public class EventProducerServiceImpl implements EventProducerService {


	@Autowired
	private SimpleEventPublisher simpleEventPublisher;
	
	@Autowired
	private EventPublisherWithKey eventPublisherWithKey;
	
	@Autowired
	private EventPublisherWithCallBack eventPublisherWithCallBack;
	
	@Autowired
	private EventPublisherWithHeaders eventPublisherWithHeaders;
	
	@Autowired
	private SynchronousEventPublisher synchronousEventPublisher;
	
	/**
	 * This will publish event to USER-EVENT topic without KEY
	 */
	
	@Override
	public UserCreatedEvent simpleEventPublisher(UserCreatedEvent userCreatedEvent) {
		userCreatedEvent.setId(UUID.randomUUID().toString()); // Once data is saved, it will generate ID.
		return simpleEventPublisher.pushlishEvent(userCreatedEvent);
	}

	/**
	 * This will publish event to USER-EVENT topic with key, this will make sure next event goes to same key goes to same partition
	 * 
	 */
	
	@Override
	public UserCreatedEvent eventPublisherWithKey(UserCreatedEvent userCreatedEvent) {
		return eventPublisherWithKey.pushlishEvent(userCreatedEvent);
	}
	

	/**
	 * This will publish event to USER-EVENT topic with key, this will make sure
	 * next event goes to same key goes to same partition.
	 * 
	 * ListenableFuture will allow you too take some action when you need to do something after event is published OR after event gets published
	 */
	
	@Override
	public UserCreatedEvent eventPublisherWithCallBack(UserCreatedEvent userCreatedEvent) {
		return eventPublisherWithCallBack.pushlishEvent(userCreatedEvent);
	}
	
	/**
	 * This will publish event to USER-EVENT topic with key, this will make sure next event goes to same key goes to same partition. 
	 * Also while publishing event we are sending Token as header
	 * 
	 * ListenableFuture will allow you too take some action when you need to do something after event is published OR after event gets published
	 */
	
	@Override
	public UserCreatedEvent eventPublisherWithHeader(UserCreatedEvent userCreatedEvent) {
		return eventPublisherWithHeaders.pushlishEvent(userCreatedEvent);
	}

	@Override
	public UserCreatedEvent synchronousEventPublisher(UserCreatedEvent userCreatedEvent) {
		return synchronousEventPublisher.pushlishEvent(userCreatedEvent);
	}
	
	

}
