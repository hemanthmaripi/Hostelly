package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Room;



@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
	Optional<Room> findByRoomnumber(Integer roomnumber);
	boolean existsByHostelidAndRoomnumber(Integer hostelid, Integer roomnumber);
	List<Room> findByHostelid(int hostelid);
	Optional<Room>findByRoomnumberAndHostelid(Integer Roomnumber, Integer hostelid);
	
}
