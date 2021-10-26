package com.ktable.service;

import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

public interface OrderBinder {

	@Input("customer-input-channel")
	GlobalKTable<String, String> customerInputStream();

	@Input("order-input-channel")
	KStream<String, String> orderInputStream();

}
