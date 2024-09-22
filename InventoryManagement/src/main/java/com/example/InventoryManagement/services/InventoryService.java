package com.example.InventoryManagement.services;

import java.util.List;

import com.example.InventoryManagement.dtos.InventoryItemDto;
import com.example.InventoryManagement.dtos.InventoryItemPaginationDto;

public interface InventoryService {
	
	public InventoryItemDto addItem(InventoryItemDto inventoryItemDto);
	
	public InventoryItemDto getItemById(Long id);
	
	public InventoryItemDto getItemByName(String name);
	
	public InventoryItemPaginationDto getAllItems(int pageNo, int pageSize);
	
	public InventoryItemDto updateItem(InventoryItemDto itemDto);
	
	public void deleteItem(Long id);
	
	public void deleteItemByName(String name);
	
	public List<InventoryItemDto> getItemByCategory(String name);
}
