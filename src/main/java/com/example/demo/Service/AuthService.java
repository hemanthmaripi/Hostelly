package com.example.demo.Service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Hostel;
import com.example.demo.Entity.Otp;
import com.example.demo.Repository.HostelRepository;
import com.example.demo.Repository.OtpRepository;

@Service
public class AuthService {

	@Autowired
	private HostelRepository hostelRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private OtpRepository otpRepository;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	
	public Hostel login(String username, String password) {
		
		Hostel hostel = hostelRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Invalid username"));
		
		if(!passwordEncoder.matches(password, hostel.getPassword())) {
			throw new RuntimeException("Invalid Password");
		} 
		
		System.out.println(sendOtp(hostel));
		
		
		return hostel;
	}
	
	
	public Hostel register(Hostel hostel) {
		if(hostelRepository.findByUsername(hostel.getUsername()).isPresent()) {
			throw new RuntimeException("username already exists... try with anohter one...");
		}
		
		if(hostelRepository.findByEmail(hostel.getEmail()).isPresent()) {
			throw new RuntimeException("Email already exists try login...");
		}
		
		hostel.setPassword(passwordEncoder.encode(hostel.getPassword()));
		
		
		
		return hostelRepository.save(hostel);
	}
	
	public String sendOtp(Hostel hostel) {
		
		String otp = String.format("%06d", new Random().nextInt(999999));
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(hostel.getEmail());
		message.setSubject("Password Reset");
		message.setText("Hii " + hostel.getUsername() + "\n Enter the given otp to successfully reset your password /n" + otp);
		javaMailSender.send(message);
		
		otpRepository.save(new Otp(otp, hostel.getUsername()));
		System.out.println(otp);		
		return "otp sent to the email "+ hostel.getEmail().substring(0, 3)+"****@gmail.com";
		
	}
}
