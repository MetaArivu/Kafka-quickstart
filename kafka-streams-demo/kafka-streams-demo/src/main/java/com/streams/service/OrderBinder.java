package com.streams.service;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

public interface OrderBinder {

	@Input("order-details-input-channel")
	public KStream<String, String> orderInputStream();
}
