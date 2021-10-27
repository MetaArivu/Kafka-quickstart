package com.shoppping.cart.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppProperties {

	@Value("${spring.kafka.bootstrap-servers}")
	private String kafkaBootstrapServer;
	
	public String getKafkaBootstrapServer() {
		return this.kafkaBootstrapServer;
	}
}
