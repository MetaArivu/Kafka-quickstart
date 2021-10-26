package com.streams.events;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
@JsonInclude(value = Include.NON_NULL)
public class OrderDetails {

	private String id;

	private String customerId;

	private Date date;

	private List<LineItems> lineItems;

	private Address addrress;

	private double total;
 
	private double layoultyPoints;
	
	private double totalLayoultyPoints;
	
	public void addLayoultyPoints() {
		layoultyPoints = getTotal() + (getTotal() / 10);
		totalLayoultyPoints = layoultyPoints;
	}
	
	public static OrderDetails parse(String _order) {
		try {
			return new ObjectMapper().readValue(_order, OrderDetails.class);
		} catch (JsonProcessingException e) {
			log.error("Error while parsing Order JSON, Exception=" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	@JsonIgnore
	public boolean isValid() {
		return  this.id !=null && this.customerId!=null && this.date !=null
				&& this.lineItems!=null && this.lineItems.size()>0
				&& this.addrress!=null;
	}
	
	@JsonIgnore
	public boolean isIndiaValid() {
		return  this.isValid() 
				&& this.getAddrress().isIndia();
	}
	
	@JsonIgnore
	public boolean isInternationalValid() {
		return  this.isValid() 
				&& this.getAddrress().isInternational();
	}
	
	 
	
	
 
}
