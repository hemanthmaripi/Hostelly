package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Otp;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Integer> {

}
