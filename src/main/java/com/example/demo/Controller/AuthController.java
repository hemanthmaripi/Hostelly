package com.example.demo.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Hostel;
import com.example.demo.Service.AuthService;

@RestController
@RequestMapping("/hostelly")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> register (@RequestBody Hostel hostel) {
		
		try {
			Hostel registeredHostel = authService.register(hostel);
			
			return ResponseEntity.ok(Map.of("Registration Successfull",registeredHostel));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
		}	
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login (@RequestParam String username, String password) {
		try {
			System.out.println(username + " " + password);
			Hostel hostel = authService.login(username, password);
			
			return ResponseEntity.ok(Map.of("Login Successfull", hostel));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
		}
	}
	
}
