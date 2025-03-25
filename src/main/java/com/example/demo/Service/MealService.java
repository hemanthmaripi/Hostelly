package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Hostel;
import com.example.demo.Entity.Meal;
import com.example.demo.Repository.MealRepository;

import jakarta.transaction.Transactional;

@Service
public class MealService {
	
	@Autowired
	private MealRepository mealRepository;
	
	public Meal getMeal(Hostel hostel) {
		
		return mealRepository.findByHostel(hostel);
		
	}
	
	@Transactional
	public Meal addMeal(Meal meal) {
		
		mealRepository.deleteAllByHostel(meal.getHostel());
		
		return mealRepository.save(meal);
		
	}
	
	public boolean deleteMeal(Meal meal) {
		
		mealRepository.deleteAll();
		
		return true;
	}
	
}
