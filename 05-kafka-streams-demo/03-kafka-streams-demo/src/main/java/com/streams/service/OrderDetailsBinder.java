package com.streams.service;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

import com.streams.events.OrderDetails;

public interface OrderDetailsBinder {

	@Input("order-details-input-channel")
	public KStream<String, String> orderInputStream();
	
	@Output("india-order-channel")
	public KStream<String, OrderDetails> indiaOrderInputStream();
	
	@Output("international-order-channel")
	public KStream<String, OrderDetails> internationalOrderInputStream();	

	
}
