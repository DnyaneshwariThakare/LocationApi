package com.example.controller;

import com.example.service.LocationService;
import com.example.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/open/hello")
    public String sayHello() {
        return "Hello, World!";
    }

    @GetMapping("/open/allstates")
    public List<Map<String, Object>> getSystemStates() {
        return locationService.getSystemStates();
    }

    @PostMapping("/open/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");
        System.out.printf("inside login controller username is %s and password is %s",username,password);
        List<Map<String, Object>> roles= locationService.findByUsername(username,password);
        if (!roles.isEmpty()) {
            roles.forEach(role -> System.out.println("Role: " + role.get("role")));
        } else {
            System.out.println("Invalid username or password");
        }

System.out.printf("rolem in controller:%s",roles);
        String jwtToken = jwtUtil.generateToken(username);
System.out.printf("jwt token= %s ",jwtToken);
        Map<String, String> response = new HashMap<>();
        response.put("token", jwtToken);
        System.out.printf("validation of token is %s",jwtUtil.validateToken(jwtToken));
        return ResponseEntity.ok(response);
    }
}
