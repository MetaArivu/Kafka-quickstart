package com.kafka.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.producer.model.UserCreatedEvent;
import com.kafka.producer.service.EventProducerService;

import static com.kafka.producer.constant.APIConstant.USER_VERSION;

@RestController
@RequestMapping(USER_VERSION)
public class UserController {

	@Autowired
	private EventProducerService eventPrdSvc;
	
	@PostMapping("/publish/simple")
	public ResponseEntity<UserCreatedEvent> simpleEventPublisher(@RequestBody UserCreatedEvent userCreatedEvent){
		userCreatedEvent = eventPrdSvc.simpleEventPublisher(userCreatedEvent);
		return new ResponseEntity<UserCreatedEvent>(userCreatedEvent, HttpStatus.CREATED);
	}
	
	@PostMapping("/publish/key")
	public ResponseEntity<UserCreatedEvent> eventPublisherWithKey(@RequestBody UserCreatedEvent userCreatedEvent){
		userCreatedEvent = eventPrdSvc.eventPublisherWithKey(userCreatedEvent);
		return new ResponseEntity<UserCreatedEvent>(userCreatedEvent, HttpStatus.CREATED);
	}

	@PostMapping("/publish/callback")
	public ResponseEntity<UserCreatedEvent> eventPublisherWithCallBack(@RequestBody UserCreatedEvent userCreatedEvent){
		userCreatedEvent = eventPrdSvc.eventPublisherWithCallBack(userCreatedEvent);
		return new ResponseEntity<UserCreatedEvent>(userCreatedEvent, HttpStatus.CREATED);
	}
	
	@PostMapping("/publish/header")
	public ResponseEntity<UserCreatedEvent> eventPublisherWithHeader(@RequestBody UserCreatedEvent userCreatedEvent){
		userCreatedEvent = eventPrdSvc.eventPublisherWithHeader(userCreatedEvent);
		return new ResponseEntity<UserCreatedEvent>(userCreatedEvent, HttpStatus.CREATED);
	}
	
	@PostMapping("/publish/sync")
	public ResponseEntity<UserCreatedEvent> synchronousEventPublisher(@RequestBody UserCreatedEvent userCreatedEvent){
		userCreatedEvent = eventPrdSvc.synchronousEventPublisher(userCreatedEvent);
		return new ResponseEntity<UserCreatedEvent>(userCreatedEvent, HttpStatus.CREATED);
	}

}
