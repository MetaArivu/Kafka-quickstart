package com.streams.service;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.streams.events.Order;

import lombok.extern.log4j.Log4j2;

@SuppressWarnings("deprecation")
@Service
@Log4j2
@EnableBinding(value = OrderBinder.class)
public class OrderService {

	@StreamListener(value = "order-input-channel")
	public void process(KStream<String, String> input) {

		KStream<String, Order> orderStream = input.mapValues(ord -> applyLoyalty(ord));

		orderStream.foreach((k, v) -> log.info("Key={}, Value={}", k, v));

		orderStream.to("loyalty-topic", Produced.with(Serdes.String(), new JsonSerde<>(Order.class)));

	}

	private Order applyLoyalty(String _order) {
		Order order = order(_order);
		order.setTotal(order.getTotal() + (order.getTotal() / 10));
		return order;
	}

	private Order order(String _order) {
		try {
			return new ObjectMapper().readValue(_order, Order.class);
		} catch (JsonProcessingException e) {
			log.error("Error while parsing Order JSON, Exception=" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
