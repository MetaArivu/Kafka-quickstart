package com.shopping.cart.service;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Service;

import com.shopping.cart.event.CartEvent;
import com.shopping.cart.event.ProductEvent;

import lombok.extern.log4j.Log4j2;

@SuppressWarnings("deprecation")
@Service
@Log4j2
@EnableBinding(value = CartLineItemEnhancerBinder.class)
public class CartLineItemEnhancerService {

	@StreamListener
	public void process(@Input("product-input-channel") GlobalKTable<String, String> products,
			@Input("cart-input-channel") KStream<String, String> cartEventStream) {

		cartEventStream.foreach((k, v) -> log.info("Key={}, Value={}", k, v));

		cartEventStream.map((k, v) -> {
			CartEvent event = CartEvent.parse(v);
			return KeyValue.pair(event.getItemId(), event);
		})
		.peek((k,v) -> log.info("Cart Event Key={}, Value={}",k,v))
		.join(products,
			  (productId, cartEvent)-> productId,
			  (cartEvent, _product)-> {
				  ProductEvent prodEvent = ProductEvent.parse(_product);
				  return new CartEvent(cartEvent.getCustomerId(), cartEvent.getItemId(), 
						  prodEvent.getProductName(), prodEvent.getPrice(), 
						  cartEvent.getQty(), cartEvent.getEventType());
			  })
		.peek((k,v) -> log.info("Enhanced Cart Key: {}, Value: {}", k, v))
		.to("cart-details-event", Produced.with(Serdes.String(), new JsonSerde<>(CartEvent.class)));
		
	}
}
