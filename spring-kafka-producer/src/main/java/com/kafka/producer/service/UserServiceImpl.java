package com.kafka.producer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.context.annotation.RequestScope;

import com.kafka.producer.model.UserCreatedEvent;

import lombok.extern.slf4j.Slf4j;

@Service
@RequestScope
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;

	@Override
	public UserCreatedEvent createUser(UserCreatedEvent userCreatedEvent) {
		userCreatedEvent.setId(UUID.randomUUID().toString());

		ListenableFuture<SendResult<String, UserCreatedEvent>> listenableFuture = 
				kafkaTemplate.send(buildProducerRecord(userCreatedEvent, "user-event", "Bearer AuthTokenId"));
				//kafkaTemplate.send("user-event", userCreatedEvent.getId(), userCreatedEvent);

		listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, UserCreatedEvent>>() {

			@Override
			public void onSuccess(SendResult<String, UserCreatedEvent> result) {
				log.info("Ack Received, Message published successfully on user-event, key={}", result.getProducerRecord().key());
			}

			@Override
			public void onFailure(Throwable ex) {
				log.error("Message cannot be published ");
			}

		});

		log.info("User created event published on user-event, key={}", userCreatedEvent.getId());
		return userCreatedEvent;
	}

	private ProducerRecord<String, UserCreatedEvent> buildProducerRecord(UserCreatedEvent event, String topic, String tokenId) {

		List<Header> recordHeaders = new ArrayList<Header>();
		recordHeaders.add(new RecordHeader("tokenId", tokenId.getBytes()));


		return new ProducerRecord<String, UserCreatedEvent>(topic, null, event.getId(), event, recordHeaders);
	}

}
