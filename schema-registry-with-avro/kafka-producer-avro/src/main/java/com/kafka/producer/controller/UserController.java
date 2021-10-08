package com.kafka.producer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.producer.avro.model.UserCreatedEvent;
import com.kafka.producer.service.EventProducerService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private EventProducerService eventPrdSvc;
	
	@PostMapping("/publish/simple")
	public ResponseEntity<UserCreatedEvent> simpleEventPublisher(@RequestBody UserCreatedEvent userCreatedEvent){
		userCreatedEvent = eventPrdSvc.simpleEventPublisher(userCreatedEvent);
		return new ResponseEntity<UserCreatedEvent>(userCreatedEvent, HttpStatus.CREATED);
	}
	 

}
