package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.LocationService;

@RestController
@RequestMapping("/api")
public class LocationController {
@Autowired
public LocationService locationService;
    @GetMapping("/hello")
    public String sayHello() {
        System.out.println("Inside /api/hello");
        return "Hello, World!";
    }
    @GetMapping("/allstates")
    public List<Map<String, Object>> getSystemStates() {
        return locationService.getSystemStates();
    }
}
