package com.kafka.producer.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserCreatedEvent {

	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private Date dob;
	
}
