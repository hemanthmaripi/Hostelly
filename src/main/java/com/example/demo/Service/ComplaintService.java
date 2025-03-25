package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Complaint;
import com.example.demo.Entity.Hostel;
import com.example.demo.Repository.ComplaintRepository;

@Service
public class ComplaintService {
	
	@Autowired
	private ComplaintRepository complaintRepository;
	
	
	public List<Complaint> getComplaints(Hostel hostel) {
		
		return complaintRepository.findByHostel(hostel);
		
	}
	
	public Complaint registerComplaint(Complaint complaint) {
		
		return complaintRepository.save(complaint);
		
	}
	
	public Complaint updateComplaint(Complaint complaint) {
		
		complaint = complaintRepository.findById(complaint.getId()).orElseThrow(()-> new RuntimeException("Complaint No longer Exists"));
		
		return complaintRepository.save(complaint);
	}
	
	
	public void deleteComplaint(Complaint complaint) {
		complaintRepository.delete(complaint);
	}
	
}
