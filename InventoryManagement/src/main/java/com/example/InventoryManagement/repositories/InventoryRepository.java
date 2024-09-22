package com.example.InventoryManagement.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.InventoryManagement.models.InventoryItem;

public interface InventoryRepository extends JpaRepository<InventoryItem, Long>{
	
	public Optional<InventoryItem> findByName(String name);
}
