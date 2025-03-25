package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Complaint;
import com.example.demo.Entity.Hostel;
import com.example.demo.Entity.Hosteller;
import com.example.demo.Entity.User;
import com.example.demo.Repository.ComplaintRepository;
import com.example.demo.Repository.HostellerRepository;
import com.example.demo.Service.ComplaintService;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/complaint")
public class ComplaintController {

	@Autowired
	private ComplaintService complaintService;
	@Autowired
	private HostellerRepository hostellerRepository;
	@Autowired
	private ComplaintRepository complaintRepository;
	
	
	 @GetMapping("/{hostelId}")
	public ResponseEntity<Object> getComplaintsByHostel(@PathVariable int hostelId) {
		 try {
			Hostel hostel = new Hostel();
			hostel.setId(hostelId);
			List<Complaint> complaints = complaintService.getComplaints(hostel);
			return ResponseEntity.ok(complaints);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	 }
	
	@PostMapping("/add-complaint")
	public ResponseEntity<Object> registerComplaint(@RequestBody Complaint complaint, HttpServletRequest request) {
		try {
			
			User user = (User) request.getAttribute("authenticatedUser");
			
			Hosteller hosteller = hostellerRepository.findById(user.getId()).orElseThrow(()-> new RuntimeException("User not found by this id"));
			
			complaint.setHostel(user.getHostel());
			complaint.setHosteller(hosteller);
			
			complaint = complaintService.registerComplaint(complaint);
			
			return ResponseEntity.ok(complaint);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	
	@PutMapping("/update-complaint")
	public ResponseEntity<Object> updateComplaint(@RequestBody Complaint updatedComplaint) {
		
		try {
			
			Complaint originalComplaint = complaintRepository.findById(updatedComplaint.getId()).orElseThrow(()-> new RuntimeException("nocomplaint with this ID"));
			originalComplaint.setOwnerDescription(updatedComplaint.getOwnerDescription());
			originalComplaint.setStatus(updatedComplaint.getStatus());
			
			updatedComplaint = complaintService.registerComplaint(originalComplaint);
			
			return ResponseEntity.ok(updatedComplaint);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			
		}
		
	}
	
	
	@DeleteMapping("/delete-complaint")
	public ResponseEntity<Object> deleteComplaint(@RequestBody Complaint complaint) {
		try {
			complaintService.deleteComplaint(complaint);
			return ResponseEntity.ok("Complaint Deleted Succesfully");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
