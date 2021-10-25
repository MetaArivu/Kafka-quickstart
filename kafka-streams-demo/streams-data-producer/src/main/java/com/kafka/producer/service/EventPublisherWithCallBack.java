package com.kafka.producer.service;

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
public class EventPublisherWithCallBack {

	@Autowired
	private KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;
	
	
	public UserCreatedEvent pushlishEvent(UserCreatedEvent userCreatedEvent) {

		ListenableFuture<SendResult<String, UserCreatedEvent>> listenableFuture =
				kafkaTemplate.send("user-event", userCreatedEvent.getId(), userCreatedEvent);

		listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, UserCreatedEvent>>() {

			@Override
			public void onSuccess(SendResult<String, UserCreatedEvent> result) {
				log.info("Ack Received, Message published successfully on user-event, key={}", result.getProducerRecord().key());
			}

			@Override
			public void onFailure(Throwable ex) {
				log.error("Message cannot be published Exception={}", ex.getMessage());
				ex.printStackTrace();
			}

		});

		log.info("3: Event Published with key = {}", userCreatedEvent.getId());

		return userCreatedEvent;
	}
}
