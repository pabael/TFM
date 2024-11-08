package com.tfm.tfm.response;

import java.util.ArrayList;
import java.util.List;

public class CategoriaResponse {
	
	private String nombre;
	private List<String> subcategorias = new ArrayList<>();

	public CategoriaResponse() {}
	
	public CategoriaResponse(String nombre, List<String> subcategorias) {
		this.nombre = nombre;
		this.subcategorias = subcategorias;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<String> getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(List<String> subcategorias) {
		this.subcategorias = subcategorias;
	}
}
