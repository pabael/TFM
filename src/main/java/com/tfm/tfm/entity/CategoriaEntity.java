package com.tfm.tfm.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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
	
	@ManyToMany (cascade = CascadeType.ALL)
	private List<SubcategoriaEntity> subcategorias = new ArrayList<>();
	
	public CategoriaEntity() {}
	
	public CategoriaEntity(String nombre) {
		this.nombre = nombre;
	}

	public CategoriaEntity(String nombre, List<SubcategoriaEntity> subcategorias) {
		this.nombre = nombre;
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

	public List<MarcaEntity> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<MarcaEntity> marcas) {
		this.marcas = marcas;
	}

	public List<SubcategoriaEntity> getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(List<SubcategoriaEntity> subcategorias) {
		this.subcategorias = subcategorias;
	}
	
	public void addSubcategoria(SubcategoriaEntity subcategoria) {
		this.subcategorias.add(subcategoria);
		subcategoria.addCategoria(this);
	}
	
	public void deleteSubcategoria(SubcategoriaEntity subcategoria) {
		this.subcategorias.remove(subcategoria);
		subcategoria.deleteCategoria(this);
	}
}
