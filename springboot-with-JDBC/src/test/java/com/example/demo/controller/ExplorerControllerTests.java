package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.controller.ExplorerRestController;
import com.example.demo.entity.Explorer;
import com.example.demo.service.ExplorerService;

@ExtendWith(MockitoExtension.class)
public class ExplorerControllerTests {
	
	@Mock
	private ExplorerService explorerService;
	
	@InjectMocks
	private ExplorerRestController controller;
	
	private Explorer explorer1;
	private Explorer explorer2;
	
	@BeforeEach
	void setUp() {
		explorer1 = new Explorer(1L, "Jai", "jai@123.com");
		explorer2 = new Explorer(2L, "Ray", "ray@123.com");
	}
	
	@Test
	void getAllExplorersTest() {
		// Arrange
		when(explorerService.getAllExplorers()).thenReturn(Arrays.asList(explorer1,explorer2));
		
		// Act
		List<Explorer> explorers = controller.getAllExplorers();
		
		// Assert
		assertNotNull(explorers);
		assertEquals(2, explorers.size());
		assertEquals("Ray", explorers.get(1).getName());
		verify(explorerService, times(1)).getAllExplorers();
	}
	
	@Test
	void findExplorerByIdTest() {
		// Arrange
		when(explorerService.findExplorerById(1L)).thenReturn(explorer1);
		
		// Act
		Explorer explorer = controller.findExplorerById(1L);
		
		// Assert
		assertNotNull(explorer);
		assertEquals("Jai", explorer.getName());
		verify(explorerService, times(1)).findExplorerById(1L);
	}
	
	@Test
	void saveExplorerTest() {
		// Arrange 
		when(explorerService.saveExplorer(explorer1)).thenReturn(1);
		
		// Act
		int rowAffected = controller.saveExplorer(explorer1);
		
		// Assert
		assertEquals(1, rowAffected);
		verify(explorerService, times(1)).saveExplorer(explorer1);
	}
	
	@Test
	void updateExplorerTest() {
		// Arrange
		when(explorerService.updateExplorer(explorer1)).thenReturn(1);
		
		// Act
		int rowAffected = controller.updateExplorer(1L, explorer1);
		
		// Assert
		assertEquals(1, rowAffected);
		verify(explorerService, times(1)).updateExplorer(explorer1);
	}
	
	@Test
	void deleteExplorerByIdTest() {
		// Arrange
		when(explorerService.deleteExplorerById(1L)).thenReturn(1);
		
		// Act
		int rowAffected = controller.deleteExplorerById(1L);
		
		// Assert
		assertEquals(1, rowAffected);
		verify(explorerService, times(1)).deleteExplorerById(1L);
	}
	
	
}
