package com.kafka.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.producer.model.UserCreatedEvent;
import com.kafka.producer.service.UserService;

import static com.kafka.producer.constant.APIConstant.USER_VERSION;

@RestController
@RequestMapping(USER_VERSION)
public class UserController {

	@Autowired
	private UserService userSvc;
	
	@PostMapping("/")
	public ResponseEntity<UserCreatedEvent> createUser(@RequestBody UserCreatedEvent userCreatedEvent){
		
		userCreatedEvent = userSvc.createUser(userCreatedEvent);
		return new ResponseEntity<UserCreatedEvent>(userCreatedEvent, HttpStatus.CREATED);
	}
}
