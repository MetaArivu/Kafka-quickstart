package com.streams.service;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

import com.streams.events.Order;

public interface OrderBinder {

	@Input("order-input-channel")
	public KStream<String, String> orderInputStream();
}
