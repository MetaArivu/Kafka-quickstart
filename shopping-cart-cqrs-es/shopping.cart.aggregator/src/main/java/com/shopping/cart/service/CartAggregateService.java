package com.shopping.cart.service;

import static com.shopping.cart.service.CartEventHandler.apply;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Service;

import com.shopping.cart.event.CartEvent;
import com.shopping.cart.event.ShoppingCart;

import lombok.extern.log4j.Log4j2;

@SuppressWarnings("deprecation")
@Service
@Log4j2
@EnableBinding(value = CartBinder.class)
public class CartAggregateService {

	@StreamListener(value = "cart-details-input-channel")
	public void process(KStream<String, String> input) {

		input.foreach((k, v) -> log.info("Key={}, Value={}", k, v));

		KStream<String, ShoppingCart> shoppingCartKStream = input.mapValues(CartEvent::parse)
			.map((k,v)-> KeyValue.pair(v.getCustomerId(), v))
			.groupByKey(Grouped.with(Serdes.String(),new JsonSerde<>(CartEvent.class)))
			.aggregate(()-> new ShoppingCart.Builder().build(), 
					   (k,v,aggr) -> apply(aggr, v),
					   Materialized.with(Serdes.String(), new JsonSerde<>(ShoppingCart.class)))
			.toStream();
		
		shoppingCartKStream.foreach((k,v)->log.info("CustomerId={}, Aggregate={}",k,v));
		
		shoppingCartKStream.to("shopping-cart-aggregate", Produced.with(Serdes.String(), new JsonSerde<>(ShoppingCart.class)));

	}
	


}
