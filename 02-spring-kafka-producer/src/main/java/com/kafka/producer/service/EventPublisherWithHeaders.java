package com.kafka.producer.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.kafka.producer.model.UserCreatedEvent;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EventPublisherWithHeaders {

	@Autowired
	private KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;
	
	
	public UserCreatedEvent pushlishEvent(UserCreatedEvent userCreatedEvent) {
		ListenableFuture<SendResult<String, UserCreatedEvent>> listenableFuture =
				kafkaTemplate.send(buildProducerRecord(userCreatedEvent, "user-event", "Bearer AuthTokenId"));

		listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, UserCreatedEvent>>() {

			@Override
			public void onSuccess(SendResult<String, UserCreatedEvent> result) {
				log.info("4: Ack Received, Message published successfully on user-event, key={}", result.getProducerRecord().key());
			}

			@Override
			public void onFailure(Throwable ex) {
				log.error("4: Message cannot be published Exception={}", ex.getMessage());
				ex.printStackTrace();
			}

		});

		log.info("4: Event Published with key = {}", userCreatedEvent.getId());

		return userCreatedEvent;
	}
	
	private ProducerRecord<String, UserCreatedEvent> buildProducerRecord(UserCreatedEvent event, String topic,
			String tokenId) {

		List<Header> recordHeaders = new ArrayList<Header>();
		recordHeaders.add(new RecordHeader("tokenId", tokenId.getBytes()));

		return new ProducerRecord<String, UserCreatedEvent>(topic, null, event.getId(), event, recordHeaders);
	}

}
