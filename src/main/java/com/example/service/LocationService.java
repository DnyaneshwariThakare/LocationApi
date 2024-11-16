package com.example.service;

import java.util.List;
import java.util.Map;

import com.example.repository.LocationDao;

public interface LocationService {
	public  List<Map<String, Object>> getSystemStates();

	public List<Map<String, Object>> findByUsername(String username, String password);
}
