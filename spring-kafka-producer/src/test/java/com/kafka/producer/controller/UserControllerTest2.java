package com.kafka.producer.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.producer.model.UserCreatedEvent;
import com.kafka.producer.service.UserService;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest2 {

	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean 
	UserService userService;
	
	
	@Test
	public void testCreateUser() throws Exception {

		UserCreatedEvent userCreateEvent = UserCreatedEvent.builder()
				.dob(new Date())
				.email("ketan.gote@gmail.com")
				.firstName("Ketan ")
				.lastName("Gote")
				.build();

		String json = new ObjectMapper().writeValueAsString(userCreateEvent);
		
		
		when(userService.createUser(userCreateEvent)).thenReturn(userCreateEvent);
		//doNothing().when(userService).createUser(userCreateEvent);
		
		mockMvc.perform(post("/api/v1/")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated());
		
	}
}
