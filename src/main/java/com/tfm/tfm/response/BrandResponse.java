package com.tfm.tfm.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BrandResponse {
	
	private String name;
	private String summary;
	private String url;
	private String materials;
	private Boolean crueltyFree;
	private Boolean vegan;
	private String commitment;
	private String production;
	
	private List<Map<String, String>> categoriesAndSubcategories;
	
	private List<String> labels = new ArrayList<>();

	private List<String> consumers = new ArrayList<>();

	private int price;

	private List<String> locations = new ArrayList<>();

	public BrandResponse() {}
	
	public BrandResponse(String name, String summary, String url, String materials, Boolean crueltyFree, Boolean vegan,
			String commitment, String production, List<Map<String, String>> categoriesAndSubcategories, List<String> labels, List<String> consumers, int price, List<String> locations) {
		this.name = name;
		this.summary = summary;
		this.url = url;
		this.materials = materials;
		this.crueltyFree = crueltyFree;
		this.vegan = vegan;
		this.commitment = commitment;
		this.production = production;
		this.categoriesAndSubcategories = categoriesAndSubcategories;
		this.labels = labels;
		this.consumers = consumers;
		this.price = price;
		this.locations = locations;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMaterials() {
		return materials;
	}

	public void setMaterials(String materials) {
		this.materials = materials;
	}

	public Boolean isCrueltyFree() {
		return crueltyFree;
	}

	public void setCrueltyFree(Boolean crueltyFree) {
		this.crueltyFree = crueltyFree;
	}

	public Boolean isVegan() {
		return vegan;
	}

	public void setVegan(Boolean vegan) {
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

	public List<Map<String, String>> getCategoriesAndSubcategories() {
		return categoriesAndSubcategories;
	}

	public void setCategoriesAndSubcategories(List<Map<String, String>> categoriesAndSubcategories) {
		this.categoriesAndSubcategories = categoriesAndSubcategories;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public List<String> getConsumers() {
		return consumers;
	}

	public void setConsumers(List<String> consumers) {
		this.consumers = consumers;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}
}
