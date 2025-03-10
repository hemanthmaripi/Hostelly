package com.example.demo.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Admin;
import com.example.demo.Repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	public Admin createAdmin(Admin admin) {
		return adminRepository.save(admin);
	}
	
	public boolean findByEmail(String email) {
		Optional<Admin> a = adminRepository.findByEmail(email);
		
		if(a.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
}
