package com.tfm.tfm.response;

import java.util.ArrayList;
import java.util.List;

public class BrandResponse {
	
	private String name;
	private String summary;
	private String materials;
	private boolean crueltyFree;
	private boolean vegan;
	private String commitment;
	private String production;
	
	private List<String> categories = new ArrayList<>();
	
	private List<String> subcategories = new ArrayList<>();

	private List<String> labels = new ArrayList<>();

	public BrandResponse() {}
	
	public BrandResponse(String name, String summary, String materials, boolean crueltyFree, boolean vegan,
			String commitment, String production, List<String> categories,
			List<String> subcategories, List<String> labels) {
		this.name = name;
		this.summary = summary;
		this.materials = materials;
		this.crueltyFree = crueltyFree;
		this.vegan = vegan;
		this.commitment = commitment;
		this.production = production;
		this.categories = categories;
		this.subcategories = subcategories;
		this.labels = labels;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getMaterials() {
		return materials;
	}

	public void setMaterials(String materials) {
		this.materials = materials;
	}

	public boolean isCrueltyFree() {
		return crueltyFree;
	}

	public void setCrueltyFree(boolean crueltyFree) {
		this.crueltyFree = crueltyFree;
	}

	public boolean isVegan() {
		return vegan;
	}

	public void setVegan(boolean vegan) {
		this.vegan = vegan;
	}

	public String getCommitment() {
		return commitment;
	}

	public void setCommitment(String commitment) {
		this.commitment = commitment;
	}

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public List<String> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<String> subcategories) {
		this.subcategories = subcategories;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
}
