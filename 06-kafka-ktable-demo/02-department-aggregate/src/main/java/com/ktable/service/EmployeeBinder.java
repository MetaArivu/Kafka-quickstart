package com.ktable.service;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.Input;

public interface EmployeeBinder {

	@Input("employee-input-channel")
	public KStream<String, String>  employeeInputStream();
	
	
}
