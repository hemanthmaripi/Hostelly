package com.example.demo.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Admin;
import com.example.demo.Entity.Hostel;
import com.example.demo.Repository.HostelRepository;
import com.example.demo.Service.AdminService;

@RestController
@RequestMapping("/api/hostelly")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private HostelRepository hostelRepository;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	
	@PostMapping("/admin")
	public ResponseEntity<Object> addAdmin(@RequestBody Map<String,String> admin) {
		try {
			Admin ad = new Admin();
			ad.setName(admin.get("name"));
			ad.setEmail(admin.get("email"));
			ad.setPassword(passwordEncoder.encode(admin.get("password")));
			Hostel h = hostelRepository.findById(Integer.parseInt(admin.get("hostel"))).orElseThrow(()->new RuntimeException("Hostel doen not exist"));
			ad.setHostel(h);
			Admin a = adminService.createAdmin(ad);
			
			return ResponseEntity.ok(a);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
//	public ResponseEntity<Object> addHosteller(@RequestBody Map<String, String> hosteller) {
//		
//		try {
//			
//			String name = hosteller.get("name");
//			String phone = hosteller.get("phone");
//			String email = hosteller.get("email");
//			String password = passwordEncoder.encode(hosteller.get("phone"));
//			String address = hosteller.get("address");
//			String emergencyContact = hosteller.get("emergencyContact");
//			
//			
//			
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
//		
// }
	
}
