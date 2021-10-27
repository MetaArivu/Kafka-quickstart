package com.kafka.consumer.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.kafka.producer.avro.model.UserCreatedEvent;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserEventConsumer {

	@KafkaListener(topics = { "user-event" })
	public void simpleEventConsumer(ConsumerRecord<String, UserCreatedEvent> record) {
		log.info("1: Event Received={}", record.value());
	}

}
