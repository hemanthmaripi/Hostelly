package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.Hosteller;

public interface HostellerRepository extends JpaRepository<Hosteller, Integer> {
     Optional<Hosteller> findByEmail (String email);
     
     @Query(value = "SELECT * FROM hosteller WHERE hostel_id = :hostelId", nativeQuery = true)
     List<Hosteller> findByHostelId(@Param("hostelId") Long hostelId);

	Optional<Hosteller> findById(Long id);
	
	@Query(value = "SELECT * FROM hosteller h WHERE h.hostel_id = :hostelId AND h.room_no = :roomNo", nativeQuery = true)
	List<Hosteller> findByHostelIdAndRoomNo(@Param("hostelId") int hostelId, @Param("roomNo") int roomNo);

}
