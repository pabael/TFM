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
@Table(name = "Categoria")
public class CategoriaEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nombre;

	@ManyToMany (mappedBy = "categorias")
	private List<MarcaEntity> marcas = new ArrayList<>();
	
	@ManyToMany
	private List<SubcategoriaEntity> subcategorias = new ArrayList<>();
	
	public CategoriaEntity() {}
	
	public CategoriaEntity(String nombre) {
		this.nombre = nombre;
	}

	public CategoriaEntity(String nombre, List<MarcaEntity> marcas) {
		this.nombre = nombre;
		this.marcas = marcas;
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

	public List<MarcaEntity> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<MarcaEntity> marcas) {
		this.marcas = marcas;
	}
}
