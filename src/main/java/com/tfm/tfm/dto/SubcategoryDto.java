package com.tfm.tfm.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class SubcategoryDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(name="name", example="Pijama")
	@NotNull
	private String name;

	public SubcategoryDto() {}
	
	public SubcategoryDto(@NotNull String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
