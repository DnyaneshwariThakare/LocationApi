package com.example.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.repository.LocationDao;
@Service
public class LocationServiceImpl implements LocationService{
	  private final LocationDao locationDao;

	    // Constructor injection for LocationDao
	    public LocationServiceImpl(LocationDao locationDao) {
	        this.locationDao = locationDao;
	    }

	    @Override
	    public List<Map<String, Object>> getSystemStates() {
	        return locationDao.getSystemStates();
	    }
}
