package com.example.demo.Controller;

import java.util.Map;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Admin;
import com.example.demo.Entity.Hostel;
import com.example.demo.Service.LoginSignUpService;

@RestController
@RequestMapping("/api/hostelly")
public class LoginSignUpController {
	
	private BCryptPasswordEncoder passwordEncoder;
	private LoginSignUpService loginSignUpService;
	
	
	
	public LoginSignUpController(LoginSignUpService loginSignUpService) {
		super();
		this.loginSignUpService = loginSignUpService;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}



	@PostMapping("/register")
	public ResponseEntity<Object> signUp(@RequestBody Map<String, String> userDetails) {
		try {
			
			Hostel hostel = new Hostel();
			Admin admin = new Admin(); 
			
			hostel.setName(userDetails.get("hostelname"));
			hostel.setPhone(userDetails.get("phone"));
			hostel.setAddress(userDetails.get("address"));
			
			admin.setName(userDetails.get("name"));
			admin.setEmail("email");
			admin.setPassword(passwordEncoder.encode(userDetails.get("password")));
			
			admin = loginSignUpService.signUp(hostel, admin);
			
			return ResponseEntity.ok(admin);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
}
