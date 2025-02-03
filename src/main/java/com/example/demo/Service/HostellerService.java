package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Hostel;
import com.example.demo.Entity.Hosteller;
import com.example.demo.Entity.Room;
import com.example.demo.Repository.HostelRepository;
import com.example.demo.Repository.HostellerRepository;
import com.example.demo.Repository.RoomRepository;

@Service
public class HostellerService {
	
	private HostellerRepository hostellerRepository;
	
	private RoomRepository roomRepository;
	
	private HostelRepository hostelRepository;
	
	public HostellerService(HostellerRepository hostellerRepository, RoomRepository roomRepository, HostelRepository hostelRepository) {
		this.hostellerRepository = hostellerRepository;
		this.roomRepository = roomRepository;
		this.hostelRepository = hostelRepository;
	}
	
	public Hosteller addHosteller(int hostelid, int roomno, Hosteller hosteller) {
		
		Optional<Hostel> hostel = hostelRepository.findById(hostelid);
		
		if(!hostel.isPresent()) {
			throw new RuntimeException("Hostel not found");
		}
		
		Optional<Room> room = roomRepository.findByRoomnumberAndHostelid(roomno, hostelid);
		
		
		
		 if(!room.isPresent()) {
			 throw new RuntimeException("No room was found by this room number");
		 }
		 
		 Room r = room.get();
		 
		 if(r.getOccupancy()>=r.getCapacity()) {
			 throw new RuntimeException("Room is full try in another room");
		 }
		 
		 hosteller.setRoom(r);
		 hosteller.setRent(r.getRent());
		 hosteller.setHostel(hostel.get());
		 Hosteller h = hostellerRepository.save(hosteller);
		 
		 r.setOccupancy(r.getOccupancy()+1);
		 roomRepository.save(r);
		
		 return h;
		
	}
	
	
	public Hosteller updateHosteller(Hosteller hosteller) {
		
		return hostellerRepository.save(hosteller);
		
	}
	
	
	public List<Hosteller> getAllHostellers() {
		return hostellerRepository.findAll();
	}
	
	
	
	public void deleteHosteller(int id) {
		
		Optional<Hosteller> hosteller = hostellerRepository.findById(id);
		
		if(!hosteller.isPresent()) {
			throw new RuntimeException("No Hosteller is present with this id");
		}
		
		Room r = hosteller.get().getRoom();
		
		r.setOccupancy(r.getOccupancy()-1);
		roomRepository.save(r);
		
		
		hostellerRepository.deleteById(id);
	}
	
	
	public List<Hosteller> getHostellerByRoom(int id) {
		
		Optional<Room> r = roomRepository.findById(id); 
		
		if(!r.isPresent()) {
			throw new RuntimeException("No Room is present with that room id");
		}
		
		return hostellerRepository.findByRoom(r.get());
		
	}
	
}
