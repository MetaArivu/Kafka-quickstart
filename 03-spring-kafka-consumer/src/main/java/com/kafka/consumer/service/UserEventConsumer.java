package com.kafka.consumer.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserEventConsumer  {

	@KafkaListener(topics = {"user-event"})
	public void simpleEventConsumer(String event) {
		log.info("1: Event Received={}",event);
	}

	//@KafkaListener(topics = {"user-event"})
	public void eventConsumerWithConsumerRecord(ConsumerRecord<String, String> event) {
		log.info("2: Event Received key={}, offset={}, partition={}",event.key(), event.offset(), event.partition());
		log.info("2: Message={}",event.value());
	}
	
	//@KafkaListener(topics = { "user-event" })
	public void eventConsumerWithHeadersAndConsumerRecord(ConsumerRecord<String, String> event, @Headers MessageHeaders headers) throws Exception {

		log.info("3: Event Received key={}, offset={}, partition={}",event.key(), event.offset(), event.partition());
		log.info("3: Message={}",event.value());
		
		headers.keySet().forEach(key -> {
            Object value = headers.get(key);
            if (key.equals("tokenId")){
                log.info("3: {}: {}", key, new String((byte[])value));
            } else {
                log.info("3: {}: {}", key, value);
            }
        });
		
	}
	
	
	//@KafkaListener(topics = { "user-event" })
	public void eventConsumerWithExceptionDemo(ConsumerRecord<String, String> event, @Headers MessageHeaders headers) throws Exception {

		log.info("4: Event Received key={}, offset={}, partition={}",event.key(), event.offset(), event.partition());
		log.info("4: Message={}",event.value());
		
		headers.keySet().forEach(key -> {
            Object value = headers.get(key);
            if (key.equals("tokenId")){
                log.info("4: {}: {}", key, new String((byte[])value));
            } else {
                log.info("4: {}: {}", key, value);
            }
        });
		
		//This is demo only to show how custom exception can be handled. This will get catched in Error handler defined in com.kafka.consumer.config.KafkaConfig
		if(event.value().contains("error")) {
			throw new Exception("Custom exception ");
		}
	}

}
