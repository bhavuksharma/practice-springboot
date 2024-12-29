package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Explorer;

@Repository
public class ExplorerRepositoryImpl implements IExplorerRepository {

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public ExplorerRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Explorer> findAll() {
		// TODO Auto-generated method stub
		String theQuery = "select * from explorer";
		return jdbcTemplate.query(theQuery, new ExplorerRowMapper());
	}

	@Override
	public Explorer findById(Long id) {
		// TODO Auto-generated method stub
		String theQuery = "select * from explorer where id = ?";
		return jdbcTemplate.queryForObject(theQuery, new ExplorerRowMapper(), id);
	}

	@Override
	public int save(Explorer theExplorer) {
		// TODO Auto-generated method stub
		String theQuery = "insert into explorer (name,email) values(?,?)";
		return jdbcTemplate.update(theQuery,theExplorer.getName(), theExplorer.getEmail());
	}

	@Override
	public int update(Explorer theExplorer) {
		// TODO Auto-generated method stub
		String theQuery = "update explorer set name = ?, email = ? where id = ?";
		return jdbcTemplate.update(theQuery, theExplorer.getName(), theExplorer.getEmail(), theExplorer.getId());
	}

	@Override
	public int deleteById(Long id) {
		// TODO Auto-generated method stub
		String theQuery = "delete from explorer where id = ?";
		return jdbcTemplate.update(theQuery, id);
	}

	private static class ExplorerRowMapper implements RowMapper<Explorer>{

		@Override
		public Explorer mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Explorer theExplorer = new Explorer();
			theExplorer.setId(rs.getLong("id"));
			theExplorer.setName(rs.getString("name"));
			theExplorer.setEmail(rs.getString("email"));
			return theExplorer;
		}
		
	}
}
