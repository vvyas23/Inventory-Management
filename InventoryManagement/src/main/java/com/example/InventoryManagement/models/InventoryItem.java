package com.example.InventoryManagement.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="inventory")
public class InventoryItem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_seq_gen")
    @SequenceGenerator(name = "inventory_seq_gen", sequenceName = "inventory_seq", allocationSize = 1)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Integer quantity;
	
	@Column(nullable = false)
	private Double price;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
