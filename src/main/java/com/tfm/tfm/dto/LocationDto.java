package com.tfm.tfm.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class LocationDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(name="name", example="Calatayud")
	@NotNull
	private String name;


	public LocationDto() {}
	
	public LocationDto(@NotNull String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
