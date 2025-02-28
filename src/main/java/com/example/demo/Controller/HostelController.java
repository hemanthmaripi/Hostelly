package com.example.demo.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Admin;
import com.example.demo.Entity.Hostel;
import com.example.demo.Entity.Hosteller;
import com.example.demo.Service.AdminService;
import com.example.demo.Service.HostelService;

@RestController
@RequestMapping("/api/hostelly")
public class HostelController {
	
	@Autowired
	private HostelService hostelService;
	@Autowired
	private AdminService adminService;
	
	
	@PostMapping("/hostel")
	public ResponseEntity<Object> createHostel(@RequestBody Map<String,String> hostel) {
		try {
		String name = hostel.get("hostelname");
		String address = hostel.get("address");
		String phone = hostel.get("phone");
		
		Hostel h = new Hostel(name, address, phone);
		
		h = hostelService.addHostel(h);
		
		String adminName = hostel.get("adminname");
		String email = hostel.get("email");
		String password = hostel.get("password");
		
		
		Admin a = new Admin(adminName, email, password, h);
		
		a = adminService.createAdmin(a);
		
		return ResponseEntity.ok(a);
				
		
		
		
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
