package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Hosteller;
import com.example.demo.Entity.Room;

import java.util.List;


@Repository
public interface HostellerRepository extends JpaRepository<Hosteller, Integer> {

	List<Hosteller> findByRoom(Room room);
}
