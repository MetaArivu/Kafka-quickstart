package com.kafka.producer.service;

import com.kafka.producer.model.OrderDetails;

public interface OrderService {

	public OrderDetails publishOrder();

}
