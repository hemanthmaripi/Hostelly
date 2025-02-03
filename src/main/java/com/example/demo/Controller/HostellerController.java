package com.example.demo.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Hosteller;
import com.example.demo.Service.HostellerService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/admin/hosteller")
public class HostellerController {

	@Autowired
	private HostellerService hostellerService;
	
	@PostMapping("/add")
	public ResponseEntity<Map<String, Object>> addHosteller(@RequestParam int roomno, @RequestParam int hostelid, @RequestBody Hosteller hosteller) {
		
		try {
			return ResponseEntity.ok(Map.of("Added successfully", hostellerService.addHosteller(hostelid, roomno, hosteller)));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));
		}
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Map<String, Object>> updateHosteller(@RequestParam int id, @RequestBody Hosteller hosteller) {
		try {
			Hosteller h = hostellerService.updateHosteller(hosteller);
			
			return ResponseEntity.ok(Map.of("Updated Successfully", h));
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
		}
	}
	
	@GetMapping("/")
	public List<Hosteller> getHostellers() {
		try {
			return hostellerService.getAllHostellers();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteHosteller (@RequestParam int hostellerid){
	
		try {
			hostellerService.deleteHosteller(hostellerid);
			return ResponseEntity.ok("Deleted Successfully");
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}	
	}
	
	@GetMapping("/room")
	public ResponseEntity<List<Hosteller>> getHostellerByRoomNo(@RequestParam int id) {
		try {
			 List<Hosteller> h = hostellerService.getHostellerByRoom(id);
			 
			 return ResponseEntity.ok().body(h);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.badRequest().body(null);
		}
	}
	
}
