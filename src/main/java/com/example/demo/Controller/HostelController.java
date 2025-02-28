package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Hostel;
import com.example.demo.Entity.Hosteller;
import com.example.demo.Service.HostelService;

@RestController
@RequestMapping("/api/hostelly")
public class HostelController {
	
	@Autowired
	private HostelService hostelService;
	
	
	@PostMapping("/hostel")
	public ResponseEntity<Object> createHostel(@RequestBody Hostel hostel) {
		try {
		Hostel h = hostelService.addHostel(hostel);
		
		return ResponseEntity.ok(h);
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e);
		}
	}
	
	
//	public List<Hosteller> getAllHostellers() {
//		try {
//			
//		}
//	}
}
