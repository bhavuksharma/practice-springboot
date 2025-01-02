package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class User {

	@JsonProperty("user_id")
	private Long id;
	
	@JsonProperty("user_name")
	private String name;
	
	@JsonProperty(value = "email_address", required = true)
	private String email;
	
	@JsonProperty("age")
	private int age = 18; // default value
	
	@JsonProperty()
	private List<Address> address;
}
