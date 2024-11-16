package com.example.repository;

import java.util.List;
import java.util.Map;

public interface LocationDao {
	  public List<Map<String, Object>> getSystemStates();

	public List<Map<String, Object>> findByUsername(String username, String password);
}
