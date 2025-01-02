package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService {

	public List<User> readUserFromJsonFile(String filePath) throws StreamReadException, DatabindException, IOException {
		
		// Create Objectmapper instance
		ObjectMapper mapper = new ObjectMapper();
		
		// Create file instance for file path
		File jsonFile = new File(filePath);
		
		// Read the json file and convert it to a User object
		
//		return mapper.readValue(jsonFile, User.class); //to deserialize json to User object
		return mapper.readValue(jsonFile, new TypeReference<List<User>>() {}); // to deserialize json array into List<User>

	}
}
