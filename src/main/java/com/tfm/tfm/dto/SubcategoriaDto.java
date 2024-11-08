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
	
	@Schema(name="categorias", type="array", example="[\"Ropa\"]")
	private List<String> categorias;

	public SubcategoriaDto() {}
	
	public SubcategoriaDto(@NotNull String nombre, List<String> categorias) {
		this.nombre = nombre;
		this.categorias = categorias;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<String> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<String> categorias) {
		this.categorias = categorias;
	}
}
