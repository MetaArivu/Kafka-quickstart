package com.kafka.producer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.TestPropertySource;

import com.kafka.producer.model.UserCreatedEvent;
import com.kafka.producer.service.EventProducerServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EmbeddedKafka(topics = { "user-event" }, partitions = 3)
@TestPropertySource(properties = { "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}",
		"spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}" })
public class UserControllerTest1 {

	@Autowired
	EmbeddedKafkaBroker embeddedKafkaBroker;

	@Autowired
	TestRestTemplate restTemplate;

	private Consumer<String, String> consumer;

	@BeforeEach
	void setUp() {
		Map<String, Object> configs = new HashMap<>(KafkaTestUtils.consumerProps("user-create-group1", "true", embeddedKafkaBroker));
		consumer = new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), new StringDeserializer()).createConsumer();
		embeddedKafkaBroker.consumeFromAllEmbeddedTopics(consumer);
	}

	@AfterEach
	void tearDown() {
		consumer.close();
	}

	@Test
	public void testCreateUser() {

		UserCreatedEvent userCreateEvent = UserCreatedEvent.builder()
				.dob(new Date())
				.email("ketan.gote@gmail.com")
				.firstName("Ketan ")
				.lastName("Gote")
				.build();

		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		header.add("Authorization", "Bearer AuthToken");

		HttpEntity<UserCreatedEvent> httpEntity = new HttpEntity<UserCreatedEvent>(userCreateEvent, header);

		ResponseEntity<UserCreatedEvent> responseEntity = restTemplate.exchange("/api/v1/publish/simple", HttpMethod.POST, httpEntity,
				UserCreatedEvent.class);

		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

		ConsumerRecord<String, String> consumerRecord = KafkaTestUtils.getSingleRecord(consumer, "user-event");
		
		String value = consumerRecord.value();
		
		log.info("Event Data={}",consumerRecord.value());
		//assertEquals(value.length()>0, value);
		assertNotNull(value);

	}
}
