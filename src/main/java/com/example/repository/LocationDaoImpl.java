package com.example.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;

@Repository
public class LocationDaoImpl implements LocationDao {

    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(LocationDaoImpl.class);

    public LocationDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Map<String, Object>> getSystemStates() {
        String sql = "SELECT * FROM Systemstates";
        try {
            return jdbcTemplate.queryForList(sql);
        } catch (Exception e) {
            logger.error("Error fetching system states: ", e);
            throw new RuntimeException("Error fetching system states", e);  // Rethrow or handle properly
        }
    }
    @Override
    public List<Map<String, Object>> findByUsername(String username, String password) {
        String sql = "SELECT id FROM users WHERE username = ? AND password = ?";
        try {
            logger.info("Executing SQL: {} with parameters: {}, {}", sql, username, password);
            return jdbcTemplate.queryForList(sql, username, password);
        } catch (Exception e) {
            logger.error("Error fetching user roles for username: {}. Exception: {}", username, e.getMessage());
            throw new RuntimeException("Error fetching user roles", e);
        }
    }


}
