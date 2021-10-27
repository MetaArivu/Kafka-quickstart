package com.kafka.producer.model;

public class Address {

	private String label;
	private String address1;
	private String address2;
	private String city;
	private String province;
	private String county;
	private String postalCode;

	public Address(String label, String address1, String address2, String city, String province, String county,
			String postalCode) {
		super();
		this.label = label;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.province = province;
		this.county = county;
		this.postalCode = postalCode;
	}

	public String getLabel() {
		return label;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getCity() {
		return city;
	}

	public String getProvince() {
		return province;
	}

	public String getCounty() {
		return county;
	}

	public String getPostalCode() {
		return postalCode;
	}

}
