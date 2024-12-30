package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Explorer;
import com.example.demo.repository.IExplorerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ExplorerService {

	private final IExplorerRepository explorerRepository;
	
	@Autowired
	public ExplorerService(IExplorerRepository explorerRepository) {
		this.explorerRepository = explorerRepository;
	}
	
	public List<Explorer> getAllExplorers(){
		return explorerRepository.findAll();
	}
	
	public Explorer findExplorerById(Long id) {
		return explorerRepository.findById(id);
	}
	
	public int saveExplorer(Explorer theExplorer) {
		int rowsaffected = explorerRepository.save(theExplorer);
		return rowsaffected;
	}
	
	public int updateExplorer(Explorer theExplorer) {
		return explorerRepository.update(theExplorer);
	}
	
	public int deleteExplorerById(Long id) {
		return explorerRepository.deleteById(id);
	}
	
}
