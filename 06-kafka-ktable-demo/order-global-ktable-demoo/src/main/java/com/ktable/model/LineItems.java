package com.ktable.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class LineItems {

	private String itemId;
	private String item;
	private int qty;
	private double price;
	private double total;
	
	 
	
	
	
	
}
