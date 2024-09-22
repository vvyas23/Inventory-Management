package com.example.InventoryManagement.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.InventoryManagement.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	public Optional<Category> findByName(String name);
}
