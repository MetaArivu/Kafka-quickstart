package com.kafka.consumer.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

//@Component
@Slf4j
public class UserEventConsumerWithManualAck implements AcknowledgingMessageListener<String, String> {
	
	@Override
	@KafkaListener(topics = { "user-event" })
	public void onMessage(ConsumerRecord<String, String> data, Acknowledgment acknowledgment) {
		log.info("Message Consumed={}",data);
		acknowledgment.acknowledge();
		log.info("Message acknowledge");
	}

}
