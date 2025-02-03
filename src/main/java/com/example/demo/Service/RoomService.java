package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Room;
import com.example.demo.Repository.RoomRepository;

@Service
public class RoomService {
	
	@Autowired
	private RoomRepository roomRepository;
	
	
	public Room addRoom(Room room) {
		
		if(roomRepository.existsByHostelidAndRoomnumber(room.getHostelid(), room.getRoomnumber())) {
			throw new RuntimeException("Room number already taken give another number");
		}
		
		Room updatedRoom = roomRepository.save(room);
		
		return updatedRoom;
	}
	
	
	public Room updateRoom(Room room) {
		Room updatedRoom = roomRepository.save(room);
		
		return updatedRoom;
	}
	
	
	public List<Room> getAllRooms(int hostelid) {
		
		return roomRepository.findByHostelid(hostelid);
		
	}
	
}
