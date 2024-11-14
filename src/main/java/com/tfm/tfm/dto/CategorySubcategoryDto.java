package com.tfm.tfm.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class CategorySubcategoryDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(name="category", example="ropa")
	@NotNull
	private String category;
	
	@Schema(name="subcategories", type="array", example="[\"deporte\", \"vaqueros\"]")
	@NotNull
	private List<String> subcategories;

	public CategorySubcategoryDto() {}
	
	public CategorySubcategoryDto(@NotNull String category, @NotNull List<String> subcategories) {
		this.category = category;
		this.subcategories = subcategories;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<String> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<String> subcategories) {
		this.subcategories = subcategories;
	}

}
