package com.example.InventoryManagement.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.InventoryManagement.dtos.CategoryDto;
import com.example.InventoryManagement.services.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@PostMapping
	public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
		CategoryDto savedCategory = categoryService.addCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(savedCategory, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAllCategories() {
		List<CategoryDto> categoryDtoList = categoryService.getAllCategories();
		return ResponseEntity.ok(categoryDtoList);
	}
}
