package com.streams.service;


import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@SuppressWarnings("deprecation")
@Service
@Log4j2
@EnableBinding(value = CartBinder.class)
public class CartAggregateService {

	@StreamListener(value = "cart-input-channel")
	public void process(KStream<String, String> input) {

		input.foreach((k, v) -> log.info("Key={}, Value={}", k, v));

		input.mapValues(CustomerCartEvent::parse)
			//.peek((k,v) -> log.info("Peek1 Key={}, Value={}",k,v))
			.map((k,v)-> KeyValue.pair(v.getCustomerId(), v))
			//.peek((k,v) -> log.info("Peek1 Key={}, Value={}",k,v))
			.groupByKey(Grouped.with(Serdes.String(),new JsonSerde<>(CustomerCartEvent.class)))
			.aggregate(()-> new ShoppingCart(), 
					   (k,v,aggr) -> {
						   //log.info("Aggr1 k={}, v={}, aggr={}",k,v,aggr);
						   LineItems lineItems = new LineItems();
						   lineItems.setItemId(v.getItemId());
						   lineItems.setQty(v.getQty());
						   aggr.setCustomerId(v.getCustomerId());
						   aggr.addLineItem(lineItems);
						   return aggr;
					   },
					   Materialized.with(Serdes.String(), new JsonSerde<>(ShoppingCart.class)))
			.toStream()
			.foreach((k,v)->log.info("Aggregate Key={}, Value={}",k,v));
		
		/*
		input.mapValues(CustomerCartEvent::parse)
				.map((k,v)-> KeyValue.pair(v.getCustomerId(), v))
				.peek((k,v) -> log.info("Peek1 Key={}, Value={}",k,v))
				.toTable(Materialized.with(Serdes.String(), new JsonSerde<>(CustomerCartEvent.class)))
				.groupBy((k,v) -> KeyValue.pair(v.getCustomerId(), v),
						Grouped.with(Serdes.String(),new JsonSerde<>(CustomerCartEvent.class)))
				.aggregate(()-> new ShoppingCart(), 
						(k,v,aggr)-> {
							log.info("Aggr1 key={}, value={}",k,v);
							return aggr;
						}, 
						(k,v,aggr)-> {
							log.info("Aggr2 key={}, value={}",k,v);
							return new ShoppingCart();
						})
				.toStream()
				.foreach((k,v)->log.info("foreach Key={}, Value={}",k,v));
		*/
		// orderStream.to("loyalty-topic", Produced.with(Serdes.String(), new
		// JsonSerde<>(OrderDetails.class)));

	}

}
