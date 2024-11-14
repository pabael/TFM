package com.tfm.tfm.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Subcategory")
public class SubcategoryEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;

	@ManyToMany (mappedBy = "subcategories")
	private List<BrandEntity> brands = new ArrayList<>();

	@ManyToMany (mappedBy = "subcategories")
    private List<CategoryEntity> categories = new ArrayList<>();
	
	public SubcategoryEntity() {}
	
	public SubcategoryEntity(String name) {
		this.name = name;
	}
	
	public SubcategoryEntity(String name, List<CategoryEntity> categories) {
		this.name = name;
		this.categories = categories;
	}
	
	public SubcategoryEntity(String name, List<BrandEntity> brands, List<CategoryEntity> categories) {
		this.name = name;
		this.brands = brands;
		this.categories = categories;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BrandEntity> getBrands() {
		return brands;
	}

	public void setBrands(List<BrandEntity> brands) {
		this.brands = brands;
	}

	public List<CategoryEntity> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryEntity> categories) {
		this.categories = categories;
	}
	
	public void addCategory(CategoryEntity category) {
		this.categories.add(category);
	}
	
	public void deleteCategory(CategoryEntity category) {
		this.categories.removeIf(cat -> cat.equals(category));
	}
}
