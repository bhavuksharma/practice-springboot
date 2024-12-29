package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Explorer;

public interface IExplorerRepository {

	public List<Explorer> findAll();
	public Explorer findById(Long id);
//	public List<Explorer> findById(Long id);
	public int save(Explorer theExplorer);
	public int update(Explorer theExplorer);
	public int deleteById(Long id);
	
}
