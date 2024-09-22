package com.example.InventoryManagement.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.InventoryManagement.LearningBeans.Greetings;

@RestController
@RequestMapping("/api/learning")
public class LearningController {
	
	private Greetings greetings;
	
	public LearningController(@Qualifier("ChristmasGreetings1")Greetings greetings) {
		this.greetings = greetings;
	}
	
	//Learning methods
	@GetMapping("/greeting")
	public ResponseEntity<String> getGreeting() {
		return ResponseEntity.ok(greetings.greetings());
	}

}
