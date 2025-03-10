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

import com.example.demo.Entity.Hostel;
import com.example.demo.Entity.Room;
import com.example.demo.Entity.RoomId;
import com.example.demo.Repository.HostelRepository;
import com.example.demo.Service.RoomService;

@RestController
@RequestMapping("/api/hostelly")
public class RoomContoller {

	@Autowired
	private RoomService roomService;

	@Autowired
	private HostelRepository hostelRepository;

	@PostMapping("/room")
	public ResponseEntity<Object> addRoom(@RequestBody Map<String, String> room) {

		try {

			int roomno = Integer.parseInt(room.get("roomno"));
			int hostelid = Integer.parseInt(room.get("hostelid"));
			RoomId ri = new RoomId(roomno, hostelid);
			int capacity = Integer.parseInt(room.get("capacity"));
			Hostel h = hostelRepository.findById(hostelid)
					.orElseThrow(() -> new RuntimeException("Hostel does not exists"));
			Room r = new Room(ri, h, capacity);

			r = roomService.addRom(r);

			return ResponseEntity.ok(r);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Its should send bd request..");
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@GetMapping("/rooms")
	public ResponseEntity<List<Room>> getRooms(@RequestParam(required = false) int roomNo) {

		try {
			System.out.println("In room controller");
			List<Room> rooms = roomService.getAllRooms();

			return ResponseEntity.ok(rooms);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(null);
		}

	}
	
	

	@PutMapping("/room")

	public ResponseEntity<Object> updateRoom(@RequestBody Map<String, String> room) {
		try {
			int roomno = Integer.parseInt(room.get("roomno"));
			int hostelid = Integer.parseInt(room.get("hostelid"));
			RoomId ri = new RoomId(roomno, hostelid);
			int capacity = Integer.parseInt(room.get("capacity"));
			Hostel h = hostelRepository.findById(hostelid)
					.orElseThrow(() -> new RuntimeException("Hostel does not exists"));
			Room r = new Room(ri, h, capacity);

			r = roomService.updateRoom(r);

			return ResponseEntity.ok(r);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
