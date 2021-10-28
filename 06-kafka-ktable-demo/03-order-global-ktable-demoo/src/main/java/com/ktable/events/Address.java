package com.ktable.events;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class Address {

	private String label;
	private String address1;
	private String address2;
	private String city;
	private String province;
	private String county;
	private String postalCode;
 
	
	public boolean isIndia() {
		return this.getCounty()!=null && this.getCounty().equalsIgnoreCase("India");
	}
	
	public boolean isInternational() {
		return this.getCounty()!=null && !this.getCounty().equalsIgnoreCase("India");
	}
}
