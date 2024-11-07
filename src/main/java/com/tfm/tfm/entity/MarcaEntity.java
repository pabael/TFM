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
@Table(name = "Marca")
public class MarcaEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nombre;
	private String resumen;
	private String materiales;
	private boolean crueltyFree;
	private boolean vegano;
	private String compromiso;
	private String procesoProduccion;
	
	@ManyToMany
	private List<CategoriaEntity> categorias = new ArrayList<>();
	
	@ManyToMany
	private List<SubcategoriaEntity> subcategorias = new ArrayList<>();

	public MarcaEntity() {}

	public MarcaEntity(String nombre, String resumen, String materiales, boolean crueltyFree, boolean vegano,
			String compromiso, String procesoProduccion, List<CategoriaEntity> categorias,
			List<SubcategoriaEntity> subcategorias) {
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public List<CategoriaEntity> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CategoriaEntity> categorias) {
		this.categorias = categorias;
	}	
}
