package com.example.demo.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Admin;
import com.example.demo.Entity.Hostel;
import com.example.demo.Repository.AdminRepository;
import com.example.demo.Repository.HostelRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service
public class UserService {

	private HostelRepository hostelRepository;
	private AdminRepository adminRepository;
	private JavaMailSender mailSender;

	public UserService(HostelRepository hostelRepository, AdminRepository adminRepository, JavaMailSender mailSender) {
		super();
		this.hostelRepository = hostelRepository;
		this.adminRepository = adminRepository;
		this.mailSender = mailSender;
		
	}
	
	
	 
	 
}
