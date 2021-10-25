package com.streams.service;



import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import com.streams.events.Order;

import lombok.extern.log4j.Log4j2;

@SuppressWarnings("deprecation")
@Service
@Log4j2
@EnableBinding(value = OrderBinder.class)
public class OrderService {

	@StreamListener(value = "order-input-channel")
    public void process(KStream<String, String> input) {

        KStream<String, String> orderStream = input
                .mapValues(ord -> ord.toString());

        orderStream.foreach((k, v) -> log.info("Key={}, Value={}",k,v));

        orderStream.to("loyalty-topic", Produced.with(Serdes.String(), Serdes.String()));
      //  return orderStream;
    }
}
