package com.tfm.tfm.dto;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.constraints.NotNull;

public class MarcaDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private String nombre;
	
	@NotNull
	private String resumen;
	private String materiales;
	private boolean crueltyFree;
	private boolean vegano;
	private String compromiso;
	private String procesoProduccion;
	
	@NotNull
	private List<String> categorias;
	
	private List<String> subcategorias;

	public MarcaDto() {}
	
	public MarcaDto(@NotNull String nombre, @NotNull String resumen, String materiales, boolean crueltyFree,
			boolean vegano, String compromiso, String procesoProduccion, @NotNull List<String> categorias,
			List<String> subcategorias) {
		this.nombre = nombre;
		this.resumen = resumen;
		this.materiales = materiales;
		this.crueltyFree = crueltyFree;
		this.vegano = vegano;
		this.compromiso = compromiso;
		this.procesoProduccion = procesoProduccion;
		this.categorias = categorias;
		this.subcategorias = subcategorias;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public String getMateriales() {
		return materiales;
	}

	public void setMateriales(String materiales) {
		this.materiales = materiales;
	}

	public boolean isCrueltyFree() {
		return crueltyFree;
	}

	public void setCrueltyFree(boolean crueltyFree) {
		this.crueltyFree = crueltyFree;
	}

	public boolean isVegano() {
		return vegano;
	}

	public void setVegano(boolean vegano) {
		this.vegano = vegano;
	}

	public String getCompromiso() {
		return compromiso;
	}

	public void setCompromiso(String compromiso) {
		this.compromiso = compromiso;
	}

	public String getProcesoProduccion() {
		return procesoProduccion;
	}

	public void setProcesoProduccion(String procesoProduccion) {
		this.procesoProduccion = procesoProduccion;
	}

	public List<String> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<String> categorias) {
		this.categorias = categorias;
	}

	public List<String> getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(List<String> subcategorias) {
		this.subcategorias = subcategorias;
	}
}