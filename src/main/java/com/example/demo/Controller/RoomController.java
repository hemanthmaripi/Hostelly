package com.example.demo.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Room;
import com.example.demo.Service.RoomService;

@RestController
@RequestMapping("/hostelly/rooms")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	
	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> getAllRooms(@RequestParam String hostelid) {
		try {
			List<Room> rooms = roomService.getAllRooms(Integer.parseInt(hostelid));
			return ResponseEntity.ok().body(Map.of("Success", rooms));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));
		}
		
	}
	
	
	@PostMapping("/add")
	public ResponseEntity<Map<String, Object>> addRoom(@RequestBody Room room) {
		
		try {
			Room addedRoom = roomService.addRoom(room);
			
			return ResponseEntity.ok(Map.of("Room added Successfully", addedRoom));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));			
		}
		
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Map<String,Object>> updateRoom(@RequestBody Room room) {
		try {
			Room updatedRoom = roomService.updateRoom(room);
			return ResponseEntity.ok(Map.of("Updated Successfully", updatedRoom));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
		}
	}
	
	
	
}
