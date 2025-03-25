package com.example.demo.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.HostellerDTO;
import com.example.demo.Entity.Admin;
import com.example.demo.Entity.Hostel;
import com.example.demo.Entity.Hosteller;
import com.example.demo.Entity.Room;
import com.example.demo.Entity.RoomId;
import com.example.demo.Entity.User;
import com.example.demo.Repository.HostelRepository;
import com.example.demo.Service.AdminService;
import com.example.demo.Service.HostellerService;
import com.example.demo.Service.RoomService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/admin/")
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private HostelRepository hostelRepository;
	@Autowired
	private HostellerService hostellerService;
	@Autowired
	private RoomService roomService;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@PostMapping("/add-admin")
	public ResponseEntity<Object> addAdmin(@RequestBody Map<String, String> admin) {
		try {
			Admin ad = new Admin();
			ad.setName(admin.get("name"));
			ad.setEmail(admin.get("email"));
			ad.setPassword(passwordEncoder.encode(admin.get("password")));
			Hostel h = hostelRepository.findById(Integer.parseInt(admin.get("hostel")))
					.orElseThrow(() -> new RuntimeException("Hostel doen not exist"));
			ad.setHostel(h);
			Admin a = adminService.createAdmin(ad);

			return ResponseEntity.ok(a);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	// Room management requests

	@PostMapping("/add-room")
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
			e.printStackTrace();
			System.out.println("Its should send bd request..");
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@GetMapping("/get-rooms")
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

	@PutMapping("/update-room")
	public ResponseEntity<Object> updateRoom(@RequestBody Room room) {
		try {

			room = roomService.updateRoom(room);

			return ResponseEntity.ok(room);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/remove-room")
	public ResponseEntity<Object> deleteRoom(@RequestParam int roomNo, HttpServletRequest request) {
		try {

			User user = (User) request.getAttribute("authenticatedUser");

			RoomId ri = new RoomId(roomNo, user.getHostel().getId());

			roomService.deleteRoom(ri);

			return ResponseEntity.ok("Succesfully Room Deleted");

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	// Hosteller management requests

	@PostMapping("/add-hosteller")
	public ResponseEntity<Object> addHosteller(@RequestBody HostellerDTO dto, HttpServletRequest request) {

		try {

			User user = (User) request.getAttribute("authenticatedUser");

			int hostelId = user.getHostel().getId();
			int roomNo = dto.getRoomNo();
			RoomId roomId = new RoomId(roomNo, hostelId);

			Room room = roomService.getRoomById(roomId);

			Hosteller hosteller = hostellerService.addHosteller(dto, room);

			return ResponseEntity.ok(hosteller);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@GetMapping("/get-hostellers")
	public ResponseEntity<Object> getHostellers(HttpServletRequest request) {

		try {
			User user = (User) request.getAttribute("authenticatedUser");

			Hostel hostel = user.getHostel();
			long id = hostel.getId();

			List<Hosteller> hostellers = hostellerService.getHostellers(id);

			return ResponseEntity.ok(hostellers);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@GetMapping("/get-hosteller/{roomid}")
	public ResponseEntity<Object> getHostellersByRoom(@PathVariable int roomid, HttpServletRequest request) {

		try {
			User user = (User) request.getAttribute("authenticatedUser");

			Hostel hostel = user.getHostel();

			List<Hosteller> hostellers = hostellerService.getHostellersByRoom(hostel.getId(), roomid);

			return ResponseEntity.ok(hostellers);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@PutMapping("/update-hosteller")
	public ResponseEntity<Object> updateHosteller(@RequestBody Hosteller hosteller) {
		try {
			hosteller = hostellerService.updateHosteller(hosteller);

			return ResponseEntity.ok(hosteller);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
