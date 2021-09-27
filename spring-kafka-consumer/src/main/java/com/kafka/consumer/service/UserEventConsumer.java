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


	@KafkaListener(topics = { "user-event" })
	public void createUser(ConsumerRecord<String, String> event, @Headers MessageHeaders headers) throws Exception {

		log.info("Event Received={}", event);
		
		headers.keySet().forEach(key -> {
            Object value = headers.get(key);
            if (key.equals("tokenId")){
                log.info("{}: {}", key, new String((byte[])value));
            } else {
                log.info("{}: {}", key, value);
            }
        });
		
		// This is demo only to show how custom exception can be handled. This will get catched in Error handler defined in com.kafka.consumer.config.KafkaConfig
		if(event.value().contains("error")) {
			throw new Exception("Custom exception ");
		}
	}

}
