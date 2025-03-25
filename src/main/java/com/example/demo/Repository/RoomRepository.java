package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Hostel;
import com.example.demo.Entity.Room;
import com.example.demo.Entity.RoomId;
import java.util.List;


@Repository
public interface RoomRepository extends JpaRepository<Room, RoomId> {
	List<Room> findByHostel(Hostel hostel);
}
