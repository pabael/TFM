package com.tfm.tfm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfm.tfm.dto.CategoriaDto;
import com.tfm.tfm.dto.SubcategoriaDto;
import com.tfm.tfm.entity.CategoriaEntity;
import com.tfm.tfm.entity.SubcategoriaEntity;
import com.tfm.tfm.repository.CategoriaRepository;
import com.tfm.tfm.repository.SubcategoriaRepository;
import com.tfm.tfm.response.CategoriaResponse;
import com.tfm.tfm.response.SubcategoriaResponse;

@Service
public class CategoriaSubcategoriaServiceImpl implements CategoriaSubcategoriaService{

	@Autowired private CategoriaRepository categoriaRepository;
	@Autowired private SubcategoriaRepository subcategoriaRepository;

	//Categorias
	
	public CategoriaResponse createCategoria(CategoriaDto categoriaDto) {
		
		CategoriaEntity categoriaEntity = getCategoriaEntity(categoriaDto);
		
		categoriaRepository.save(categoriaEntity);
		
		return getCategoriaResponse(categoriaEntity);
		
	}
	
	private CategoriaEntity getCategoriaEntity(CategoriaDto categoriaDto) {
		
		return new CategoriaEntity(
				categoriaDto.getNombre(), 
				getListSubcategoriaEntity(categoriaDto.getSubcategorias())
				);
	}
	
	private CategoriaResponse getCategoriaResponse(CategoriaEntity categoriaEntity) {
	
		List<String> subcategories = new ArrayList<>();
		categoriaEntity.getSubcategorias().forEach(subcategory -> {
			subcategories.add(subcategory.getNombre());
		});
		
		return new CategoriaResponse(categoriaEntity.getNombre(), subcategories);
	}
	
	public List<CategoriaEntity> getListCategoriaEntity(List<String> categories) {
		List<CategoriaEntity> validCategories = new ArrayList<>();

		categories.forEach(category -> {
			var categoriaEntity = categoriaRepository.findByNombre(category);
			if(categoriaEntity.isPresent()) 
				validCategories.add(categoriaEntity.get());
		});
		
		if(validCategories.isEmpty()) {
			var categoriaNone = categoriaRepository.findByNombre("None");
			if(categoriaNone.isPresent()) {
				validCategories.add(categoriaNone.get());
			} else {
				CategoriaEntity none = new CategoriaEntity("None");
				categoriaRepository.save(none);
				validCategories.add(none);
			}
		}
		
		return validCategories;
	}
	
	//Subcategorias
	
	public SubcategoriaResponse createSubcategoria(SubcategoriaDto subcategoriaDto) {
		
		SubcategoriaEntity subcategoriaEntity = getSubcategoriaEntity(subcategoriaDto);
		
		subcategoriaRepository.save(subcategoriaEntity);
		
		return getSubcategoriaResponse(subcategoriaEntity);
		
	}
	
	private SubcategoriaEntity getSubcategoriaEntity(SubcategoriaDto subcategoriaDto) {
		
		return new SubcategoriaEntity(
				subcategoriaDto.getNombre(), 
				getListCategoriaEntity(subcategoriaDto.getCategorias())
				);
	}
	
	private SubcategoriaResponse getSubcategoriaResponse(SubcategoriaEntity subcategoriaEntity) {
	
		List<String> categories = new ArrayList<>();
		subcategoriaEntity.getCategorias().forEach(category -> {
			categories.add(category.getNombre());
		});
		
		return new SubcategoriaResponse(subcategoriaEntity.getNombre(), categories);
	}
	
	public List<SubcategoriaEntity> getListSubcategoriaEntity(List<String> subcategories) {
		List<SubcategoriaEntity> validSubcategories = new ArrayList<>();

		subcategories.forEach(subcategory -> {
			var subcategoriaEntity = subcategoriaRepository.findByNombre(subcategory);
			if(subcategoriaEntity.isPresent()) 
				validSubcategories.add(subcategoriaEntity.get());
		});
		
		if(validSubcategories.isEmpty()) {
			var subcategoriaNone = subcategoriaRepository.findByNombre("None");
			if(subcategoriaNone.isPresent()) {
				validSubcategories.add(subcategoriaNone.get());
			} else {
				SubcategoriaEntity none = new SubcategoriaEntity("None");
				subcategoriaRepository.save(none);
				validSubcategories.add(none);
			}
		}
		
		return validSubcategories;
	}
}
