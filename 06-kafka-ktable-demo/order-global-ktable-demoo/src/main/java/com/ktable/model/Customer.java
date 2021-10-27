package com.ktable.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Customer {

	private String customerId;
	
	private String customerName;
	
	private Address addrress;
	
	public Customer() {
		
	}

	public Customer(String customerId, String customerName, Address address) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.addrress = address;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Address getAddrress() {
		return addrress;
	}

	public void setAddrress(Address addrress) {
		this.addrress = addrress;
	}

	 
	
	public static Customer parse(String _customer) {
		try {
			return new ObjectMapper().readValue(_customer, Customer.class);
		} catch (JsonProcessingException e) {
			log.error("Error while parsing Order JSON, Exception=" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
