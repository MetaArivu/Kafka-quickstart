package com.streams.service;


import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import com.streams.events.Order;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@EnableBinding(value = OrderBinder.class)
public class OrderService {

	@StreamListener(value = "order-input-channel")
    @SendTo("order-output-channel")
    public KStream<String, Order> process(KStream<String, Order> input) {

        KStream<String, Order> orderStream = input
                .mapValues(ord -> new Order(ord.getOrderId(), (ord.getTotal()) + (ord.getTotal()/10)));

        orderStream.foreach((k, v) -> log.info("Key={}, Value={}",k,v));

        return orderStream;
    }
}
