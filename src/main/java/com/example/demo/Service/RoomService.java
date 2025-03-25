package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Hostel;
import com.example.demo.Entity.Room;
import com.example.demo.Entity.RoomId;
import com.example.demo.Repository.RoomRepository;

@Service
public class RoomService {
	
	@Autowired
	private RoomRepository roomRepository;
	
	public Room addRom(Room room) {
		
		
			RoomId rd = room.getId();
			Optional<Room> r = roomRepository.findById(rd);
			if(r.isPresent()) {
				throw new RuntimeException("Room Already Exists With that number give another number");
			} else {
			return roomRepository.save(room);
			}
		
	}
	
	
	public List<Room> getAllRooms(Hostel hostel ) {
		
		return roomRepository.findByHostel(hostel);
		
	}
	
	
	public Room updateRoom(Room room) {
		
		roomRepository.findById(room.getId()).orElseThrow(()-> new RuntimeException("Room is nor prest with this ID"));
		
		Room r = roomRepository.save(room);
		
		return r;
	}
	
	
	public void deleteRoom(RoomId id) {
		Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found with this number")) ;
		
		roomRepository.delete(room);
		
	}
	
	public Room getRoomById(RoomId roomId) {
		return roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found with this number"));
	}
	
}
