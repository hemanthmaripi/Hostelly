package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Hostel;
import java.util.Optional;




@Repository
public interface HostelRepository extends JpaRepository<Hostel, Integer> {
	
	Optional<Hostel> findByUsername(String username);
	
	Optional<Hostel> findByEmail(String email);
}
