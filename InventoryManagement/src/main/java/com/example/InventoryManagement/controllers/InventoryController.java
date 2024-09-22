package com.example.InventoryManagement.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.InventoryManagement.dtos.CategoryDto;
import com.example.InventoryManagement.dtos.InventoryItemDto;
import com.example.InventoryManagement.dtos.InventoryItemPaginationDto;
import com.example.InventoryManagement.services.CategoryService;
import com.example.InventoryManagement.services.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
	
	private InventoryService inventoryService;
	
	public InventoryController(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}
	
	@PostMapping
	public ResponseEntity<InventoryItemDto> addItem(@RequestBody InventoryItemDto inventoryItemDto) {
		InventoryItemDto savedItem = inventoryService.addItem(inventoryItemDto);
		return new ResponseEntity<InventoryItemDto>(savedItem, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<InventoryItemDto> getItemById(@PathVariable Long id) {
		InventoryItemDto itemDto = inventoryService.getItemById(id);
		return ResponseEntity.ok(itemDto);
	}
	
	@GetMapping("/item/{name}")
	public ResponseEntity<InventoryItemDto> getItemByName(@PathVariable String name) {
		InventoryItemDto itemDto = inventoryService.getItemByName(name);
		return ResponseEntity.ok(itemDto);
	}
	
	@GetMapping
	public ResponseEntity<InventoryItemPaginationDto> getAllItems(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize
			) {
		InventoryItemPaginationDto itemPageDto = inventoryService.getAllItems(pageNo, pageSize);
		return ResponseEntity.ok(itemPageDto);
	}
	
	@PutMapping
	public ResponseEntity<InventoryItemDto> updateItem(@RequestBody InventoryItemDto itemDto) {
		InventoryItemDto updatedItem = inventoryService.updateItem(itemDto);
		return ResponseEntity.ok(updatedItem);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
		inventoryService.deleteItem(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/item/{name}")
	public ResponseEntity<Void> deleteItemByName(@PathVariable String name) {
		inventoryService.deleteItemByName(name);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/category/{name}")
	public ResponseEntity<List<InventoryItemDto>> getItemByCategory(@PathVariable String name) {
		List<InventoryItemDto> itemDtos = inventoryService.getItemByCategory(name);
		return ResponseEntity.ok(itemDtos);
	}
	
}
