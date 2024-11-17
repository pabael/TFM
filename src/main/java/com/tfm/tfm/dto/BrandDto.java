package com.tfm.tfm.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class BrandDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(name="name", example="Clotsy")
	@NotNull
	private String name;
	
	@Schema(name="summary", example="Empresa valenciana.")
	private String summary;
	
	@Schema(name="materials", example="algodón orgánico")
	private String materials;
	
	@Schema(name="crueltyFree", type="boolean",example="true")
	private Boolean crueltyFree;
	
	@Schema(name="vegan", type="boolean", example="true")
	private Boolean vegan;
	
	@Schema(name="commitment", example="Plantan árboles.")
	private String commitment;
	
	@Schema(name="production", example="Pequeños artesanos. ")
	private String production;
	
	@Schema(name="categories", type="array", example="[\"ropa\", \"calzado\"]")
	private List<String> categories;
	
	@Schema(name="subcategories", type="array", example="[\"pijamas\"]")
	private List<String> subcategories;

	@Schema(name="labels", type="array", example="[\"GOTS\"]")
	private List<String> labels;

	@Schema(name="consumers", type="array", example="[\"Mujer\"]")
	private List<String> consumers;

	@Schema(name="price", example="1")
	private Integer price;

	@Schema(name="locations", type="array", example="[\"Calatayud\"]")
	private List<String> locations;

	public BrandDto() {}
	
	public BrandDto(@NotNull String name, String summary, String materials, Boolean crueltyFree,
			Boolean vegan, String commitment, String production, List<String> categories,
			List<String> subcategories, List<String> labels, List<String> consumers, Integer price,List<String> locations) {
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

	public List<String> getConsumers() {
		return consumers;
	}

	public void setConsumers(List<String> consumers) {
		this.consumers = consumers;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}
}
