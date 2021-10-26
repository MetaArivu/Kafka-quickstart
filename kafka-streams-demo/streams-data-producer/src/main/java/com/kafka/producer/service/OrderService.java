package com.kafka.producer.service;

import com.kafka.producer.model.OrderDetails;

public interface OrderService {

	public OrderDetails publishOrder();

	public OrderDetails publishInvalidOrder();

	public OrderDetails publishInternationalOrder();

	public OrderDetails publishOrderWithCustomerId(String id);
}
