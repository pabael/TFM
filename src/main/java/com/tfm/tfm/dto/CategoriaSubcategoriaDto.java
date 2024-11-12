package com.tfm.tfm.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class CategoriaSubcategoriaDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(name="categoria", example="ropa")
	@NotNull
	private String categoria;
	
	@Schema(name="subcategorias", type="array", example="[\"deporte\", \"vaqueros\"]")
	@NotNull
	private List<String> subcategorias;

	public CategoriaSubcategoriaDto() {}
	
	public CategoriaSubcategoriaDto(@NotNull String categoria, @NotNull List<String> subcategorias) {
		this.categoria = categoria;
		this.subcategorias = subcategorias;
	}
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<String> getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(List<String> subcategorias) {
		this.subcategorias = subcategorias;
	}

}
