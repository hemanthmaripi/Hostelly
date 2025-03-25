package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Hostel;
import com.example.demo.Entity.Meal;
import com.example.demo.Entity.User;
import com.example.demo.Service.MealService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/meal")
public class MealController {

	@Autowired
	private MealService mealService;

	@PostMapping("/add-meal")
	public ResponseEntity<Object> addMeals(@RequestBody Meal meal, HttpServletRequest request) {
		try {
			System.out.println(meal.getBreakfast());
			User user = (User) request.getAttribute("authenticatedUser");
			meal.setHostel(user.getHostel());

			meal = mealService.addMeal(meal);

			return ResponseEntity.ok(meal);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@GetMapping("/get-meal")
	public ResponseEntity<Object> getMeals(HttpServletRequest request) {
		try {

			User user = (User) request.getAttribute("authenticatedUser");
			Hostel hostel = user.getHostel();

			Meal meal = mealService.getMeal(hostel);

			if (meal != null) {
				return ResponseEntity.ok(meal);
			}

			return ResponseEntity.ok("No Meal updated Today");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@DeleteMapping("/remove-meal")
	public ResponseEntity<Object> removeMeals(@RequestBody Meal meal) {
		try {

			mealService.deleteMeal(meal);
			return ResponseEntity.ok("Meal details removed successfully");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
