package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.demo.model.User;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

public class UserServiceTests {

	@Test
	void testReadUserFromJsonFile() throws StreamReadException, DatabindException, IOException {
		// Arrange
		UserService userService = new UserService();
		String testFilePath = "src/test/resources/test-users.json";
		
		// Act
		List<User> users = userService.readUserFromJsonFile(testFilePath);
		
		//Assert
		assertNotNull(users, "list of users cannot be empty");
		assertEquals(2, users.size(), "The user list size should be 2");
		
		// Validate the first user
		User user1 = users.get(0);
		assertEquals(1L, user1.getId());
		assertEquals("rin", user1.getName());
		assertEquals("rin@123.com", user1.getEmail());
		assertNotNull(user1.getAddress(), "address should not be null");
		assertEquals("123 main", user1.getAddress().get(0).getStreet());
		assertEquals("whitefield", user1.getAddress().get(0).getCity());
		assertEquals("1234", user1.getAddress().get(0).getZipcode());
		
	}
}
