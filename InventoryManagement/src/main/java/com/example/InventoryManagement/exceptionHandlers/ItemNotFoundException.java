package com.example.InventoryManagement.exceptionHandlers;

public class ItemNotFoundException extends RuntimeException {
	public ItemNotFoundException(Long id) {
		super("Item not found with id: " + id);
	}
	
	public ItemNotFoundException(String name) {
		super("Item not found with name: " + name);
	}
}
