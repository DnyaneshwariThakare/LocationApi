package com.example.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // Generate a secure signing key of sufficient length for HS512
    private final SecretKey signingKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
   
    private final long jwtExpirationMs = 3600000; // 1 hour

    // Generate a token
    public String generateToken(String username) {
    	System.out.printf("signingKey: %s%n", signingKey);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiration
                .signWith(signingKey)  // Use the secure signing key
                .compact();
    }

    // Validate a token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false; // Invalid token
        }
    }

    // Extract username from the token
    public String extractUsername(String token) {
    	System.out.printf("token in extractUsername::%s and signingKey %s",token,signingKey);

    	System.out.printf("username inside extract username",Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
    	
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
