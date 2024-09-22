package com.example.InventoryManagement.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.InventoryManagement.dtos.CategoryDto;
import com.example.InventoryManagement.exceptionHandlers.CategoryNotFoundException;
import com.example.InventoryManagement.models.Category;
import com.example.InventoryManagement.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	CategoryRepository categoryRepository;
	
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {
		Category category = convertToEntity(categoryDto);
		Category savedCategory = categoryRepository.save(category);
		return convertToDto(savedCategory);
	}

	@Override
	public void deleteCategory(Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		if(category.isEmpty()) {
			throw new CategoryNotFoundException(id);
		}
		categoryRepository.delete(category.get());
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categoryList = categoryRepository.findAll();
		List<CategoryDto> content= categoryList.stream().map(this::convertToDto).collect(Collectors.toList());
		return content;
	}
	
	private Category convertToEntity(CategoryDto categoryDto) {
		Category category = new Category();
		category.setId(categoryDto.getId());
		category.setName(categoryDto.getName());
		return category;
	}
	
	private CategoryDto convertToDto(Category category) {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setId(category.getId());
		categoryDto.setName(category.getName());
		return categoryDto;
	}

}
