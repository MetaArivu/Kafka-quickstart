package com.kafka.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.producer.model.OrderDetails;
import com.kafka.producer.service.OrderService;

import static com.kafka.producer.constant.APIConstant.API_VERSION;

import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping(API_VERSION)
public class OrderController {

	@Autowired
	private OrderService eventPrdSvc;

	@PostMapping("/publish/")
	public ResponseEntity<OrderDetails> publishOrder() {
		OrderDetails event = eventPrdSvc.publishOrder();
		return new ResponseEntity<OrderDetails>(event, HttpStatus.CREATED);
	}
	
	 

}
