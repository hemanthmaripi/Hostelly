package com.example.demo.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Admin;
import com.example.demo.Entity.Hostel;
import com.example.demo.Entity.Hosteller;
import com.example.demo.Repository.AdminRepository;
import com.example.demo.Repository.HostelRepository;
import com.example.demo.Repository.HostellerRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service
public class AuthService {
	
	private HostelRepository hostelRepository;
	private AdminRepository adminRepository;
	private HostellerRepository hostellerRepository;
	private JavaMailSender mailSender;
	private BCryptPasswordEncoder passwordEncoder;
	
	public AuthService(HostelRepository hostelRepository, AdminRepository adminRepository, JavaMailSender mailSender, HostellerRepository hostellerRepository) {
		super();
		this.hostelRepository = hostelRepository;
		this.adminRepository = adminRepository;
		this.mailSender = mailSender;
		this.hostellerRepository = hostellerRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}
	
	// Stores Hostel and Admin Details
		@Transactional
		public Admin signUp(Hostel hostel, Admin admin) {
			hostel = hostelRepository.save(hostel);

			admin.setHostel(hostel);

			admin = adminRepository.save(admin);

			return admin;
		}
		
		
		
		public Object authenticate(String email, String password, String role) {
			
			if(role.equals("admin")) {
				
				System.out.println(email);
				Optional<Admin> ad = adminRepository.findByEmail(email);
				
				if(ad.isEmpty()) {
					throw new RuntimeException("Email not found");
				} else {
					Admin admin = ad.get();
					System.out.println(admin.getPassword());
					System.out.println(passwordEncoder.encode(password));
				
				if(!passwordEncoder.matches(password,admin.getPassword())) {
					throw new RuntimeException("Invalid Password");
				}
				
				return admin;
				}
				
			} else {
				
				Hosteller hosteller = hostellerRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("Invalid Password"));
				
				if(!passwordEncoder.matches(password, hosteller.getPassword())) {
					throw new RuntimeException("Invalid Password");
				}
				
				return hosteller;
			}
			
		}

		
		public void forgotPassword(String email, String password) {
			
			Admin admin = adminRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found with this email"));
			
			admin.setPassword(passwordEncoder.encode(password));
			adminRepository.save(admin);
			
		}
		
		// Generates OTP
		public String generateOtp() {
			String otp = String.valueOf(100000 + new Random().nextInt(900000));  // Generates 6-digit OTP
			return otp;
		}


		// Creates Hash Code to based on OTP
		public String hashOtp(String otp) {
			try {

				MessageDigest md = MessageDigest.getInstance("SHA-256");
				
				byte[] hash = md.digest(otp.getBytes());
				
				StringBuilder hexString = new StringBuilder();

				for (byte b : hash) {
					hexString.append(String.format("%02x", b));
				}
				
				return hexString.toString();
				
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				throw new RuntimeException("Error hashing OTP", e);
			}
		}

		
		// Sends OTP to the mail
		public void sendOtpEmail(String email, String otp) {
			
			SimpleMailMessage message = new SimpleMailMessage();
			
	        message.setTo(email);
	        message.setSubject("Your OTP Code");
	        message.setText("Your OTP code is: " + otp + ". It is valid for 5 minutes.");
	        
	        mailSender.send(message);
		}
		
		
		
		// Stores hashed otp in client Cookie
		 public void setOtpCookie(String hashedOtp, HttpServletResponse response) {
			 
		        Cookie otpCookie = new Cookie("otp_hash", hashedOtp);
		        
		        otpCookie.setHttpOnly(true);
		        otpCookie.setSecure(true); // Requires HTTPS in production
		        otpCookie.setMaxAge(5 * 60); // Expires in 5 minutes
		        otpCookie.setPath("/");

		        response.addCookie(otpCookie);
		    }


}
