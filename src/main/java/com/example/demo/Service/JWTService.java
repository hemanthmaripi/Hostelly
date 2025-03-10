package com.example.demo.Service;


import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	private final SecretKey SIGNIN_KEY;
	
	
	
	
	public JWTService(@Value("${jwt.secret}") String jwtSecret) {
		super();
		SIGNIN_KEY = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
	}




	@SuppressWarnings("deprecation")
	public String generateToken(User user) {
		return Jwts.builder()
				.setSubject(user.getEmail())
				.claim("name", user.getName())
				.claim("role", user.getRole())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 36000000))
				.signWith(SIGNIN_KEY).compact();
	}
	
	
	@SuppressWarnings("deprecation")
	public Object validateToken(String token) {
		Claims claims = Jwts.parser()
                .setSigningKey(SIGNIN_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();

        String email = claims.getSubject();
        String role = (String) claims.get("role");
        String name = (String) claims.get("name");
        
        
        return new User(email, role, name);
	}
	
	
	
	
}
