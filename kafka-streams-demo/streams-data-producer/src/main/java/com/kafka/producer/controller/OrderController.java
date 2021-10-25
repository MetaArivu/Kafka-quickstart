package com.kafka.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.producer.model.OrderEvent;
import com.kafka.producer.service.EventProducerService;

import static com.kafka.producer.constant.APIConstant.API_VERSION;

import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping(API_VERSION)
public class OrderController {

	@Autowired
	private EventProducerService eventPrdSvc;

	@PostMapping("/publish/")
	public ResponseEntity<OrderEvent> publishOrder() {
		OrderEvent event = new OrderEvent(UUID.randomUUID().toString(), random());
		eventPrdSvc.publishOrder(event);
		return new ResponseEntity<OrderEvent>(event, HttpStatus.CREATED);
	}
	
	private Double random() {
		int minValue = 20, maxValue=20000;
        Random theRandom = new Random();
        double theRandomValue = 0.0;
        
        // Checking for a valid range-
        if( Double.valueOf(maxValue - minValue).isInfinite() == false ) 
            theRandomValue = minValue + (maxValue - minValue) * theRandom.nextDouble();
        
        return theRandomValue;
	}

}
