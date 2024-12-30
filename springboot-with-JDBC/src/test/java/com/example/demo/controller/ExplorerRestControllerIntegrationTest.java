package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.Explorer;
import com.example.demo.repository.IExplorerRepository;
import com.example.demo.service.ExplorerService;

@AutoConfigureMockMvc
@SpringBootTest
public class ExplorerRestControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private ExplorerService explorerService;
	
	@MockitoBean
	private IExplorerRepository explorerRepository;
	
	private Explorer explorer1;
	private Explorer explorer2;
	
	@BeforeEach
	void setUp() {
		explorer1 = new Explorer(1L, "king", "king@123.com");
		explorer2 = new Explorer(2L, "prince", "prince@123.com");
	}
	
	@Test
	void getAllExplorersTest() throws Exception {
		// Arrange
		when(explorerService.getAllExplorers()).thenReturn(Arrays.asList(explorer1,explorer2));
		
		// Act & Assert
		mockMvc.perform(get("/explorers"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()").value(2))
			.andExpect(jsonPath("$[0].name").value("king"))
			.andExpect(jsonPath("$[1].name").value("prince"));
	}
	
	@Test
	void findExplorerByIdTest() throws Exception {
		// Arrange 
		when(explorerService.findExplorerById(1L)).thenReturn(explorer1);
		
		// Act & Assert
		mockMvc.perform(get("/explorers/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name").value("king"));
		
		verify(explorerService).findExplorerById(1L);
		
	}
	
	@Test
	void saveExplorerTest() throws Exception {
		// Arrange
		when(explorerService.saveExplorer(any(Explorer.class))).thenReturn(1);
//		when(explorerRepository.save(any(Explorer.class))).thenReturn(1);
		
		// Act & Assert
		
		mockMvc.perform(post("/explorers")
				.contentType("application/json")
				.content("{\"name\":\"king\",\"email\":\"king@123.com\"}"))
			.andExpect(status().isOk())
			.andExpect(content().string("1"));
		
		verify(explorerService).saveExplorer(any(Explorer.class));
	}
	
	@Test
	void updateExplorerTest() throws Exception {
		// Arrange
		when(explorerService.updateExplorer(any(Explorer.class))).thenReturn(1);
		when(explorerRepository.update(any(Explorer.class))).thenReturn(1);
		
		// Act & Assert
		mockMvc.perform(put("/explorers/1")
				.contentType("application/json")
				.content("{\"name\":\"king\",\"email\":\"king@123.com\"}"))
			.andExpect(status().isOk())
			.andExpect(content().string("1"))
			.andDo(print());
	}
	
	@Test
	void deleteExplorerByIdTest() throws Exception {
		// Arrange
		when(explorerService.deleteExplorerById(1L)).thenReturn(1);
		
		// Act & Assert
		mockMvc.perform(delete("/explorers/1"))
			.andExpect(status().isOk())
			.andExpect(content().string("1"));
	}
}
