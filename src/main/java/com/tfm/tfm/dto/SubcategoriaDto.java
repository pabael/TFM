package com.tfm.tfm.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class SubcategoriaDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(name="nombre", example="Pijama")
	@NotNull
	private String nombre;

	public SubcategoriaDto() {}
	
	public SubcategoriaDto(@NotNull String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
