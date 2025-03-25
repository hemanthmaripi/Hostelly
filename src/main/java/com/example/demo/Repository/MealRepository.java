package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Meal;
import com.example.demo.Entity.Hostel;




@Repository
public interface MealRepository extends JpaRepository<Meal, Integer> {
	void deleteAllByHostel(Hostel hostel);
	
	Meal findByHostel(Hostel hostel);
}
