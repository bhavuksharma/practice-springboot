package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Explorer;
import com.example.demo.repository.IExplorerRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/explorers")
@Slf4j
public class ExplorerRestController {

	private IExplorerRepository explorerRepository;
	
	@Autowired
	public ExplorerRestController(IExplorerRepository explorerRepository) {
		this.explorerRepository = explorerRepository;
	}
	
	@GetMapping
	public List<Explorer> getAllExplorers(){
		List<Explorer> explorerList = explorerRepository.findAll();
		log.info("list of explorer returned ----> "+ "\n"+explorerList);
		return explorerList;
	}
	
	@GetMapping("/{id}")
	public Explorer findExplorerById(@PathVariable("id") Long theId){
		return explorerRepository.findById(theId);
	}
	
	@PostMapping
	public int saveExplorer(@RequestBody Explorer theExplorer) {
		return explorerRepository.save(theExplorer);
	}
	
	@PutMapping("/{id}")
	public int updateExplorer(@PathVariable("id") Long theId, @RequestBody Explorer theExplorer) {
		theExplorer.setId(theId);
		return explorerRepository.update(theExplorer);
	}
	
	@DeleteMapping("/{id}")
	public int deleteExplorerById(@PathVariable("id") Long theId) {
		return explorerRepository.deleteById(theId);
	}
}
