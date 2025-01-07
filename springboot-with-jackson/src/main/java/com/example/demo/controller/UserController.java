package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	// Endpoint to create user (Deserialization) 
	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody User theUser){
		return ResponseEntity.ok("User created successfully: "+ theUser.getName());
	}
	
	// Endpoint to get user (Serialization)
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id){
		User theUser = new User();
		theUser.setId(id);
		theUser.setName("John");
		theUser.setEmail("john@123.com");
		theUser.setAge(30);
		
		return ResponseEntity.ok(theUser);
	}
	
	// Endpoint for reading json from file
	@GetMapping("/read")
	public ResponseEntity<List<User>> readUserFromJson(){
		List<User> users;
		try {
			users = userService.readUserFromJsonFile("src/main/resources/user.json");
			return ResponseEntity.ok(users);
		} catch (Exception e) {
			System.out.println("An error occured.");
			e.printStackTrace();
			return ResponseEntity.badRequest().body(null);
		} 
		
	}
}
