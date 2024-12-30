package com.example.demo.service;

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

import com.example.demo.entity.Explorer;
import com.example.demo.repository.IExplorerRepository;

@ExtendWith(MockitoExtension.class)
public class ExplorerServiceTests {

	@Mock
	private IExplorerRepository explorerRepository;
	
	@InjectMocks
	private ExplorerService explorerService;
	
	private Explorer explorer1;
	private Explorer explorer2;
	
	@BeforeEach
	void setUp() {
		explorer1 = new Explorer(1L, "ray", "ray@123.com");
		explorer2 = new Explorer(2L, "king", "king@123.com");
	}
	
	@Test
    void getAllExplorersTest() {
		// Arrange
		when(explorerRepository.findAll()).thenReturn(Arrays.asList(explorer1,explorer2));
		
		// Act
		List<Explorer> explorerList = explorerService.getAllExplorers();
		
		//Assert
		assertNotNull(explorerList);
		assertEquals(2, explorerList.size());
		assertEquals("king",explorerList.get(1).getName());
		verify(explorerRepository, times(1)).findAll();
	}
	
	@Test
	void findExplorerByIdTest() {
		// Arrange
		when(explorerRepository.findById(1L)).thenReturn(explorer1);
		
		// Act
		Explorer theExplorer = explorerService.findExplorerById(1L);
		
		// Assert
		assertNotNull(theExplorer);
		assertEquals("ray", theExplorer.getName());
		verify(explorerRepository, times(1)).findById(1L);
	}
	
	@Test
	void saveExplorerTest() {
		// Arrange
		when(explorerRepository.save(explorer1)).thenReturn(1);
		
		// Act
		int rowsAffected = explorerService.saveExplorer(explorer1);
		
		// Assert
		assertEquals(1, rowsAffected);
		verify(explorerRepository, times(1)).save(explorer1);
	}
	
	@Test
	void updateExplorerTest() {
		// Arrange
		when(explorerRepository.update(explorer1)).thenReturn(1);
		
		// Act
		int rowsAffected = explorerService.updateExplorer(explorer1);
		
		// Assert
		assertEquals(1, rowsAffected);
		verify(explorerRepository, times(1)).update(explorer1);
	}
	
	@Test
	void deleteExplorerByIdTest() {
		// Arrange
		when(explorerRepository.deleteById(1L)).thenReturn(1);
		
		// Act
		int rowsAffected = explorerService.deleteExplorerById(1L);
		
		// Assert
		assertEquals(1, rowsAffected);
		verify(explorerRepository, times(1)).deleteById(1L);
	}
}
