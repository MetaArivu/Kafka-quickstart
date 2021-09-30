package com.kafka.producer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import com.kafka.producer.model.UserCreatedEvent;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SynchronousEventPublisher {

	@Autowired
	private KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;

	public UserCreatedEvent pushlishEvent(UserCreatedEvent userCreatedEvent) {
		try {

			SendResult<String, UserCreatedEvent> prdRecord = kafkaTemplate .send(buildProducerRecord(userCreatedEvent, "user-event", "Bearer AuthTokenId")).get();

			log.info("5: Ack Received, Message published successfully on user-event, key={}", prdRecord.getProducerRecord().key());
		} catch (InterruptedException | ExecutionException e) {
			log.error("5: Error while publishing event to kafka, Exception={}", e.getMessage());
			e.printStackTrace();
		}

		log.info("5: Event Published with key = {}", userCreatedEvent.getId());

		return userCreatedEvent;

	}

	private ProducerRecord<String, UserCreatedEvent> buildProducerRecord(UserCreatedEvent event, String topic,
			String tokenId) {

		List<Header> recordHeaders = new ArrayList<Header>();
		recordHeaders.add(new RecordHeader("tokenId", tokenId.getBytes()));

		return new ProducerRecord<String, UserCreatedEvent>(topic, null, event.getId(), event, recordHeaders);
	}
}
