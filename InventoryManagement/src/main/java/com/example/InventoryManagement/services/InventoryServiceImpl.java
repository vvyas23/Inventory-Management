package com.example.InventoryManagement.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.InventoryManagement.dtos.InventoryItemDto;
import com.example.InventoryManagement.dtos.InventoryItemPaginationDto;
import com.example.InventoryManagement.exceptionHandlers.CategoryNotFoundException;
import com.example.InventoryManagement.exceptionHandlers.ItemNotFoundException;
import com.example.InventoryManagement.models.Category;
import com.example.InventoryManagement.models.InventoryItem;
import com.example.InventoryManagement.repositories.CategoryRepository;
import com.example.InventoryManagement.repositories.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService{
	
	private InventoryRepository repository;
	
	private CategoryRepository categoryRepository;
	
	@Autowired
	public InventoryServiceImpl(InventoryRepository repository, CategoryRepository categoryRepository) {
		this.repository = repository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public InventoryItemDto addItem(InventoryItemDto inventoryItemDto) {
		InventoryItem item = convertToEntity(inventoryItemDto);
		InventoryItem savedItem = repository.save(item);
		return convertToDto(savedItem);
	}
	
	@Override
	public InventoryItemDto getItemById(Long id) {		
		Optional<InventoryItem> item = repository.findById(id);
		return convertToDto(item.orElseThrow(() -> new ItemNotFoundException(id)));
	}
	
	@Override
	public InventoryItemDto getItemByName(String name) {
		Optional<InventoryItem> item = repository.findByName(name);
		return convertToDto(item.orElseThrow(() -> new ItemNotFoundException(name)));
	}
	
	@Override
	public InventoryItemPaginationDto getAllItems(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<InventoryItem> itemsPage = repository.findAll(pageable);
		List<InventoryItem> listItems = itemsPage.getContent();
		List<InventoryItemDto> content= listItems.stream().map(this::convertToDto).collect(Collectors.toList());
		
		InventoryItemPaginationDto pageDto = new InventoryItemPaginationDto();
		pageDto.setContent(content);
		pageDto.setPageNo(itemsPage.getNumber());
		pageDto.setPageSize(itemsPage.getSize());
		pageDto.setTotalElements(itemsPage.getTotalElements());
		pageDto.setTotalPages(itemsPage.getTotalPages());
		pageDto.setLast(itemsPage.isLast());
		
		return pageDto;
		
	}
	
	@Override
	public InventoryItemDto updateItem(InventoryItemDto itemDto) {
		Optional<InventoryItem> item = repository.findById(itemDto.getId());
		InventoryItem updateItem = item.orElseThrow(() -> new ItemNotFoundException(itemDto.getId()));
		updateItem.setName(itemDto.getName());
		updateItem.setQuantity(itemDto.getQuantity());
		updateItem.setPrice(itemDto.getPrice());
		updateItem.setCategory(categoryRepository.findByName(itemDto.getCategoryName()).get());
		InventoryItemDto updateItemDto = convertToDto(repository.save(updateItem));
		return updateItemDto;
	}
	
	@Override
	public void deleteItem(Long id) {
		InventoryItem item = repository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
		repository.deleteById(item.getId());
	}
	
	@Override
	public void deleteItemByName(String name) {
		Optional<InventoryItem> item = repository.findByName(name);
		InventoryItem deleteitem = item.orElseThrow(()->new ItemNotFoundException(name));
		repository.deleteById(deleteitem.getId());
		
	}
	
	private InventoryItem convertToEntity(InventoryItemDto inventoryItemDto) {
		InventoryItem item = new InventoryItem();
		item.setId(inventoryItemDto.getId());
		item.setName(inventoryItemDto.getName());
		item.setQuantity(inventoryItemDto.getQuantity());
		item.setPrice(inventoryItemDto.getPrice());
		Optional<Category> category = categoryRepository.findByName(inventoryItemDto.getCategoryName());
		if(category.isEmpty()) {
			throw new CategoryNotFoundException(inventoryItemDto.getCategoryName());
		}
		item.setCategory(category.get());
		return item;
	}
	
	private InventoryItemDto convertToDto(InventoryItem inventoryItem) {
		InventoryItemDto itemDto = new InventoryItemDto();
		itemDto.setId(inventoryItem.getId());
		itemDto.setName(inventoryItem.getName());
		itemDto.setQuantity(inventoryItem.getQuantity());
		itemDto.setPrice(inventoryItem.getPrice());
		itemDto.setCategoryName(inventoryItem.getCategory().getName());
		return itemDto;
	}

	@Override
	public List<InventoryItemDto> getItemByCategory(String name) {
		Optional<Category> categoryOptional = categoryRepository.findByName(name);
		if(categoryOptional.isEmpty()) {
			throw new CategoryNotFoundException(name);
		}
		Category category = categoryOptional.get();
		List<InventoryItem> listItems = category.getInventoryItems();
		List<InventoryItemDto> content= listItems.stream().map(this::convertToDto).collect(Collectors.toList());
		return content;
	}

}
