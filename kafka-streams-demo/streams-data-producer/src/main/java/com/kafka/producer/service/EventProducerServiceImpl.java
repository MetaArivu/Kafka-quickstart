package com.kafka.producer.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import com.kafka.producer.model.OrderEvent;

@Service
@RequestScope
public class EventProducerServiceImpl implements EventProducerService {


	
	@Autowired
	private EventPublisherWithKey eventPublisherWithKey;
	
 
	/**
	 * This will publish event to USER-EVENT topic with key, this will make sure next event goes to same key goes to same partition
	 * 
	 */
	
	@Override
	public OrderEvent publishOrder(OrderEvent event) {
		return eventPublisherWithKey.pushlishEvent(event);
	}
	
 
	

}
