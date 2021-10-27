package com.ktable.service;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import com.ktable.model.Customer;
import com.ktable.model.OrderDetails;

import lombok.extern.log4j.Log4j2;

@SuppressWarnings("deprecation")
@Service
@Log4j2
@EnableBinding(value = OrderBinder.class)
public class OrderService {

	@StreamListener
	public void process(@Input("customer-input-channel") GlobalKTable<String, String> customers,
			@Input("order-input-channel") KStream<String, String> orders) {

		orders.foreach((k, v) -> log.info(" Order Details Key: {}, Value: {}", k, v));

		orders.map((k, v) -> {
			OrderDetails od = OrderDetails.parse(v);
			return KeyValue.pair(od.getCustomerId(), od);
		})
		.peek((k, v) -> log.info("OrderDetails Key: {}, Value: {}", k, v))
		.join(customers,
				(customerId, order) -> {
					log.info("CustomerId={}, Order={}", customerId, order);
					return customerId;
				},
                (order, _customer) -> {
                	Customer customer = Customer.parse(_customer);
                	order.setAddrress(customer.getAddrress());
                	log.info("Order={}, customer={}", order, customer);
                	return order;
                })
		
		.foreach((k,v) -> log.info("Key: {}, Value: {}", k, v));
				
		
		
	}

}
