package com.example.demo.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.example.demo.Entity.User;
import com.example.demo.Service.RoomService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/hostelly")
public class RoomContoller {

	@Autowired
	private RoomService roomService;

	@PostMapping("/room")
	public ResponseEntity<Object> addRoom(@RequestBody Map<String, String> room, HttpServletRequest request) {

		try {

			User user = (User) request.getAttribute("authenticatedUser");
			int roomno = Integer.parseInt(room.get("roomno"));
			int hostelid = user.getHostel().getId();
			RoomId ri = new RoomId(roomno, hostelid);
			int capacity = Integer.parseInt(room.get("capacity"));

			Room r = new Room(ri, user.getHostel(), capacity);

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
	public ResponseEntity<List<Room>> getRooms(@RequestParam(required = false) Integer roomNo,
			HttpServletRequest request) {

		try {

			Hostel hostel = ((User) request.getAttribute("authenticatedUser")).getHostel();
			List<Room> rooms = roomService.getAllRooms(hostel);

			return ResponseEntity.ok(rooms);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(null);
		}

	}

	@PutMapping("/room")
	public ResponseEntity<Object> updateRoom(@RequestBody Room room) {
		try {

			room = roomService.updateRoom(room);

			return ResponseEntity.ok(room);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/room")
	public ResponseEntity<Object> deleteRoom(@RequestParam int roomNo, HttpServletRequest request) {
		try {

			User user = (User) request.getAttribute("authenticatedUser");

			RoomId ri = new RoomId(roomNo, user.getHostel().getId());

			roomService.deleteRoom(ri);

			return ResponseEntity.ok("Succesfully Room Deleted");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
