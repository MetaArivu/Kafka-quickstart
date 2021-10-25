package com.kafka.producer.service;

import com.kafka.producer.model.OrderEvent;

public interface EventProducerService {

	public OrderEvent publishOrder(OrderEvent event);

}
