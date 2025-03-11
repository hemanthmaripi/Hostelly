package com.example.demo.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Admin;
import com.example.demo.Entity.Hostel;
import com.example.demo.Entity.User;
import com.example.demo.Service.AdminService;
import com.example.demo.Service.AuthService;
import com.example.demo.Service.HostelService;
import com.example.demo.Service.JWTService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private BCryptPasswordEncoder passwordEncoder;
	private AuthService authService;
	private HostelService hostelService;
	private AdminService adminService;
	private JWTService jwtService;

	public AuthController(AuthService authService, HostelService hostelService,
			AdminService adminService, JWTService jwtService) {
		super();
		this.passwordEncoder = new BCryptPasswordEncoder();
		this.authService = authService;
		this.hostelService = hostelService;
		this.adminService = adminService;
		this.jwtService = jwtService;
	}




	@PostMapping("/signup")
	public ResponseEntity<Object> signUp(@RequestBody Map<String, String> userDetails) {
		try {
			
			if(hostelService.findByPhone(userDetails.get("phone"))){
				throw new RuntimeException("Phone Number already exists");
			}
			
			if(adminService.findByEmail(userDetails.get("email"))) {
				throw new RuntimeException("Email already exists");
			}

			Hostel hostel = new Hostel();
			Admin admin = new Admin();

			hostel.setName(userDetails.get("hostelname"));
			hostel.setPhone(userDetails.get("phone"));
			hostel.setAddress(userDetails.get("address"));

			admin.setName(userDetails.get("name"));
			admin.setEmail("email");
			admin.setPassword(passwordEncoder.encode(userDetails.get("password")));
			System.out.println(admin.getPassword());
			admin = authService.signUp(hostel, admin);

			return ResponseEntity.ok(admin);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping("/send-otp")
	public ResponseEntity<Object> sendOtp(@RequestParam String email, HttpServletResponse response) {
		try {

			String otp = authService.generateOtp();
			String hashedOtp = authService.hashOtp(otp);

			authService.sendOtpEmail(email, otp);

			authService.setOtpCookie(hashedOtp, response);
			authService.setOtpCookie(hashedOtp, response);

			return ResponseEntity.ok("Sms Sent Successfully");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());

		}
	}

	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestParam String otp, HttpServletRequest request) {
		// Step 1: Retrieve the hashed OTP from the cookie
		String storedHash = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("otp_hash".equals(cookie.getName())) {
					storedHash = cookie.getValue();
				}
			}
		}

		// Step 2: Check if OTP cookie exists
		if (storedHash == null) {
			return "OTP expired or not found!";
		}

		// Step 3: Hash the entered OTP
		String hashedInputOtp = authService.hashOtp(otp);

		// Step 4: Compare hashes
		if (hashedInputOtp.equals(storedHash)) {
			return "OTP Verified! User authenticated.";
		} else {
			return "Invalid OTP!";
		}
	}
		
	
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody Map<String, String> login, HttpServletResponse response) {
		try {
			
			String email = login.get("email");
			System.out.println(email);
			String password = login.get("password");
			String role = "admin";
			
			Admin admin = (Admin) authService.authenticate(email, password, role);
			
			User user = new User(email, role, admin.getName());
			
			String token = jwtService.generateToken(user);
			
			
			// Adding Cookie to the client
			Cookie cookie = new Cookie("authToken", token);
			cookie.setHttpOnly(true);
			cookie.setPath("/");
			cookie.setMaxAge(3600);
			response.addCookie(cookie);

			response.addHeader("Set-Cookie",
					String.format("authToken=%s; HttpOnly; Path=/; Max-Age=3600; SameSite=None", token));

			Map<String, Object> responseBody = new HashMap<>();

			responseBody.put("message", "Login SuccessFull");
			responseBody.put("role", user.getRole());
			responseBody.put("username", user.getEmail());

			return ResponseEntity.ok(responseBody);
			
		} catch (RuntimeException e) {
			
			e.printStackTrace();
			return ResponseEntity.ok("We couldn't found any acoount with this email... please check and try");
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@PostMapping("/forgot-password")
	public ResponseEntity<Object> forgotPassword(@RequestBody Map<String, String> userInfo) {
		try {
			String email = userInfo.get("email");
			String password = userInfo.get("password");
			
			authService.forgotPassword(email, password);
			
			return ResponseEntity.ok("Password Resetted Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	

	
	
//	@PostMapping("/hosteller/login")
//	public ResponseEntity<?> hostellerLogin(@RequestBody Map<String, String> login) {
//		
//	}
	
}
