package com.ktabel.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktable.service.EmployeeService;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
public class Employee {

	private String empId;
	private String name;
	private String deptId;
	private double salary;
	
	
	public static Employee instance(String emp) {
		try {
			return new ObjectMapper().readValue(emp, Employee.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			log.error("Employee Parse JsonMappingException={}",e.getMessage());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			log.error("Employee Parse JsonProcessingException={}",e.getMessage());

		}
		
		return null;
	}
}
