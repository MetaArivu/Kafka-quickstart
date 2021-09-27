package com.kafka.producer.service;

import com.kafka.producer.model.UserCreatedEvent;

public interface UserService {

	public UserCreatedEvent createUser(UserCreatedEvent userCreatedEvent);
}
