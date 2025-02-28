package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Room;
import com.example.demo.Entity.RoomId;

@Repository
public interface RoomRepository extends JpaRepository<Room, RoomId> {

}
