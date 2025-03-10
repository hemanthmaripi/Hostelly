package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Hostel;


@Repository
public interface HostelRepository extends JpaRepository<Hostel, Integer> {
	Optional<Hostel>findByPhone(String phone);
}
