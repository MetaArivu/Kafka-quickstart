package com.shopping.cart.service;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

public interface CartBinder {

	@Input("cart-input-channel")
	public KStream<String, String> cartInputStream();
	
	
}
