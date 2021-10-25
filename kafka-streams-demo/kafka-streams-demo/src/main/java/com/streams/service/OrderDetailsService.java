package com.streams.service;

import java.util.function.Predicate;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import com.streams.events.OrderDetails;

import lombok.extern.log4j.Log4j2;

@SuppressWarnings("deprecation")
@Service
@Log4j2
@EnableBinding(value = OrderDetailsBinder.class)
public class OrderDetailsService {

	@StreamListener(value = "order-details-input-channel")
	@SendTo({"india-order-channel", "international-order-channel"})
	public KStream<String, OrderDetails>[] process(KStream<String, String> input) {

		KStream<String, OrderDetails> orderStream = input.mapValues(ord -> OrderDetails.parse(ord));

		orderStream.foreach((k, v) -> log.info("Key={}, Value={}", k, v.isValid()));

		orderStream.filter((k,v)-> !v.isValid())
			.to("order-error", Produced.with(Serdes.String(), new JsonSerde<>(OrderDetails.class)));

		KStream<String, OrderDetails> validOrders = orderStream
                .filter((k, v) -> v.isValid())
                .map((k, v) -> KeyValue.pair(v.getId(), v));

		validOrders.foreach((k, v) -> log.info("Publishing event Key={}, Value={}", k, v));
		
		return validOrders.branch((k,v)-> v.isIndiaValid(),(k,v)-> v.isInternationalValid());
	}
}
