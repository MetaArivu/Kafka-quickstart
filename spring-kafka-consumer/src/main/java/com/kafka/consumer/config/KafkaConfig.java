package com.kafka.consumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class KafkaConfig {

	@Autowired
	private AppProperties appProp;

	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, appProp.getKafkaBootstrapServer());
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "user-event-group");

		return props;
	}

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), new StringDeserializer());
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		
		//factory.getContainerProperties().setAckMode(AckMode.MANUAL); Uncomment this if you need to handle manual commit of message
		
		//factory.setConcurrency(3); Enable this when you want to have concurrent kafka message listener
		
		factory.setErrorHandler((exp, data)->{
			log.error("Exception = {}, While processing Message = {}",exp.getMessage(), data);
		});
		
		// This is Used for retry;
		factory.setRetryTemplate(retryTemplate());
		
		return factory;
	}

	private RetryTemplate retryTemplate() {
		RetryTemplate retryTemplate = new RetryTemplate();
		retryTemplate.setRetryPolicy(retryPolicy());
		retryTemplate.setBackOffPolicy(backoffPolciy());
		return retryTemplate;
	}

	private BackOffPolicy backoffPolciy() {
		FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
		fixedBackOffPolicy.setBackOffPeriod(2000);
		return fixedBackOffPolicy;
	}

	private SimpleRetryPolicy retryPolicy() {
		
		// Retry can be implemented on specific Exception
		
		Map<Class<? extends Throwable>, Boolean> exceptions = new HashMap<Class<? extends Throwable>, Boolean>();
		exceptions.put(Exception.class, true);
		
		//SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(3,exceptions, true);
		//retryPolicy.setMaxAttempts(3);
		
		SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(3);
		
		return retryPolicy;
	}

}
