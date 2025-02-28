package com.example.demo.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Hostel;
import com.example.demo.Repository.HostelRepository;

@Service
public class HostelService {
	
	@Autowired
	private HostelRepository hostelRepository;
	
	public Hostel addHostel(Hostel hostel) {
		try {
		return hostelRepository.save(hostel);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
