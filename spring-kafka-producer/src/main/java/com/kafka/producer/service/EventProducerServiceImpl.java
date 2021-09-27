package com.kafka.producer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

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
public class EventProducerServiceImpl implements EventProducerService {

	@Autowired
	private KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;

	/**
	 * This will publish event to USER-EVENT topic without KEY
	 */
	
	@Override
	public UserCreatedEvent simpleEventPublisher(UserCreatedEvent userCreatedEvent) {
		userCreatedEvent.setId(UUID.randomUUID().toString()); // Once data is saved, it will generate ID.

		kafkaTemplate.send("user-event", userCreatedEvent);

		log.info("1: Event published");
		return userCreatedEvent;
	}

	/**
	 * This will publish event to USER-EVENT topic with key, this will make sure next event goes to same key goes to same partition
	 * 
	 */
	
	@Override
	public UserCreatedEvent eventPublisherWithKey(UserCreatedEvent userCreatedEvent) {

		kafkaTemplate.send("user-event", userCreatedEvent.getId(), userCreatedEvent);
		log.info("2: Event Published with key = {}", userCreatedEvent.getId());

		return userCreatedEvent;
	}
	

	/**
	 * This will publish event to USER-EVENT topic with key, this will make sure
	 * next event goes to same key goes to same partition.
	 * 
	 * ListenableFuture will allow you too take some action when you need to do something after event is published OR after event gets published
	 */
	
	@Override
	public UserCreatedEvent eventPublisherWithCallBack(UserCreatedEvent userCreatedEvent) {

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
	
	/**
	 * This will publish event to USER-EVENT topic with key, this will make sure next event goes to same key goes to same partition. 
	 * Also while publishing event we are sending Token as header
	 * 
	 * ListenableFuture will allow you too take some action when you need to do something after event is published OR after event gets published
	 */
	
	@Override
	public UserCreatedEvent eventPublisherWithHeader(UserCreatedEvent userCreatedEvent) {

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

	@Override
	public UserCreatedEvent synchronousEventPublisher(UserCreatedEvent userCreatedEvent) {

		try {
			
			SendResult<String, UserCreatedEvent> prdRecord = kafkaTemplate.send(buildProducerRecord(userCreatedEvent, "user-event", "Bearer AuthTokenId")).get();
			
			log.info("5: Ack Received, Message published successfully on user-event, key={}",prdRecord.getProducerRecord().key());
		} catch (InterruptedException | ExecutionException e) {
			log.error("5: Error while publishing event to kafka, Exception={}",e.getMessage());
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
