package com.shopping.cart.service;

import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

public interface CartLineItemEnhancerBinder {

	@Input("product-input-channel")
	GlobalKTable<String, String> productInputStream();

	@Input("cart-input-channel")
	KStream<String, String> cartEventInputStream();

}
