package com.tfm.tfm.response;

import java.util.ArrayList;
import java.util.List;

public class SubcategoriaResponse {
	
	private String nombre;
	private List<String> categorias = new ArrayList<>();

	public SubcategoriaResponse() {}
	
	public SubcategoriaResponse(String nombre, List<String> categorias) {
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
