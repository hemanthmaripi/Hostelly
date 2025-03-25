package com.example.demo.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.HostellerDTO;
import com.example.demo.Entity.Hosteller;
import com.example.demo.Entity.Room;
import com.example.demo.Repository.HostellerRepository;


@Service
public class HostellerService {
	
	@Autowired
	private HostellerRepository hostellerRepository;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	
	public Hosteller addHosteller(HostellerDTO dto, Room room) {
		
		Hosteller hosteller = new Hosteller();
		
		hosteller.setName(dto.getName());
		hosteller.setEmail(dto.getEmail());
		hosteller.setAadharCardNo(dto.getAadharCardNo());
		hosteller.setAddress(dto.getAddress());
		hosteller.setDOJ(new Date());
		hosteller.setEmergencyContactNo(dto.getEmergencyContactNo());
		hosteller.setPassword(passwordEncoder.encode("1234"));
		hosteller.setPhoneno(dto.getPhoneno());
		
		if(room.getOccupied() < room.getCapacity()) {
			room.setOccupied(room.getOccupied()+1);
			hosteller.setRoom(room);
		} else {
			throw new RuntimeException("No vacancy in this room");
		}
		
		
		return hostellerRepository.save(hosteller);
		
	}
	
	public Hosteller updateHosteller(Hosteller hosteller) {
		
		Room room = hosteller.getRoom();
		
		Hosteller h = hostellerRepository.findById(hosteller.getId()).orElseThrow(() -> new RuntimeException("No user found"));

		
		if(room.equals(h.getRoom())) {
		
			if(room.getOccupied()>=room.getCapacity()) {
				throw new RuntimeException("Room Already Full");
			}
		}
		
		return hostellerRepository.save(hosteller);
	}
	
	
	public List<Hosteller> getHostellers(long id) {
		return hostellerRepository.findByHostelId(id);
	}
	
	public List<Hosteller> getHostellersByRoom(int hostelId, int roomId) {
		
		return hostellerRepository.findByHostelIdAndRoomNo(hostelId, roomId);
	}
	
	
}
