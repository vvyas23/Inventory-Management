package com.example.InventoryManagement.services;

import java.util.List;

import com.example.InventoryManagement.dtos.CategoryDto;

public interface CategoryService {
	
	public CategoryDto addCategory(CategoryDto categoryDto);
	
	public void deleteCategory(Long id);
	
	public List<CategoryDto> getAllCategories();
}
