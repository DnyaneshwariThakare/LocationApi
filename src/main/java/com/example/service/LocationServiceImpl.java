package com.example.service;

import com.example.repository.LocationDao;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationDao locationDao;
    private static final Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);

    // Constructor injection for LocationDao
    public LocationServiceImpl(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    public List<Map<String, Object>> getSystemStates() {
        logger.info("Fetching system states...");
        return locationDao.getSystemStates();
    }

	@Override
	public  List<Map<String, Object>> findByUsername(String username, String password) {
		 return locationDao.findByUsername(username,password);
	
	}
}
