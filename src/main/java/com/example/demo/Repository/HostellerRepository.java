package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Hosteller;

public interface HostellerRepository extends JpaRepository<Hosteller, Integer> {
     Optional<Hosteller> findByEmail (String email);
}
