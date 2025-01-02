package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Address {

	@JsonProperty()
	private String street;
	
	@JsonProperty()
	private String city;
	
	@JsonProperty()
	private String zipcode;
	
}
