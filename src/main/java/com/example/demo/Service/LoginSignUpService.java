package com.example.demo.Service;


import org.springframework.stereotype.Service;

import com.example.demo.Entity.Admin;
import com.example.demo.Entity.Hostel;
import com.example.demo.Repository.AdminRepository;
import com.example.demo.Repository.HostelRepository;

import jakarta.transaction.Transactional;

@Service
public class LoginSignUpService {
	
	private HostelRepository hostelRepository;
	private AdminRepository adminRepository;
	
	public LoginSignUpService(HostelRepository hostelRepository, AdminRepository adminRepository) {
		super();
		this.hostelRepository = hostelRepository;
		this.adminRepository = adminRepository;
		
	}
	
	@Transactional
	public Admin signUp(Hostel hostel, Admin admin) {
		hostel = hostelRepository.save(hostel);
		
		admin.setHostel(hostel);
		
		admin = adminRepository.save(admin);
		
		return admin;
	}
	
}
