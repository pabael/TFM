package com.tfm.tfm.response;

import java.util.ArrayList;
import java.util.List;

public class SubcategoryResponse {
	
	private String name;
	private List<String> categories = new ArrayList<>();

	public SubcategoryResponse() {}
	
	public SubcategoryResponse(String name) {
		this.name = name;
	}
	
	public SubcategoryResponse(String name, List<String> categories) {
		this.name = name;
		this.categories = categories;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
}
