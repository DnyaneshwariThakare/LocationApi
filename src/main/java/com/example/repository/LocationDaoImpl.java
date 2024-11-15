package com.example.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository  
public class LocationDaoImpl implements LocationDao{

    private final JdbcTemplate jdbcTemplate;

    // Constructor injection for JdbcTemplate
    public LocationDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Method to get all system states
    @Override
    public List<Map<String, Object>> getSystemStates() {
        String sql = "SELECT * FROM Systemstates";
        return jdbcTemplate.queryForList(sql);
    }
}
