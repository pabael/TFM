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
@Table(name = "Brand")
public class BrandEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	private String summary;
	private String materials;
	private boolean crueltyFree;
	private boolean vegan;
	private String commitment;
	private String production;
	
	@ManyToMany
	private List<CategoryEntity> categories = new ArrayList<>();
	
	@ManyToMany
	private List<SubcategoryEntity> subcategories = new ArrayList<>();

	@ManyToMany
	private List<LabelEntity> labels = new ArrayList<>();

	@ManyToMany
	private List<ConsumerEntity> consumers = new ArrayList<>();

	public BrandEntity() {}

	public BrandEntity(String name, String summary, String materials, boolean crueltyFree, boolean vegan,
			String commitment, String production, List<CategoryEntity> categories,
			List<SubcategoryEntity> subcategories, List<LabelEntity> labels, List<ConsumerEntity> consumers) {
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

	public List<CategoryEntity> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryEntity> categories) {
		this.categories = categories;
	}

	public List<SubcategoryEntity> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<SubcategoryEntity> subcategories) {
		this.subcategories = subcategories;
	}

	public List<LabelEntity> getLabels() {
		return labels;
	}

	public void setLabels(List<LabelEntity> labels) {
		this.labels = labels;
	}

	public List<ConsumerEntity> getConsumers() {
		return consumers;
	}

	public void setConsumers(List<ConsumerEntity> consumers) {
		this.consumers = consumers;
	}
}
