package com.tfm.tfm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tfm.tfm.dto.CategoriaDto;
import com.tfm.tfm.dto.CategoriaSubcategoriaDto;
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
		
		if(!categoriaRepository.findByNombre(categoriaDto.getNombre()).isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria already exits");
		
		return new CategoriaEntity(categoriaDto.getNombre());
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
		
		return validCategories;
	}
	
	private List<CategoriaResponse> getListCategoriaResponse(List<CategoriaEntity> categoriaEntityList){
		
		List<CategoriaResponse> categoriaResponseList = new ArrayList<>();
		categoriaEntityList.forEach(categoriaEntity -> categoriaResponseList.add(getCategoriaResponse(categoriaEntity)));
		return categoriaResponseList;
	}
	
	//Subcategorias
	
	public SubcategoriaResponse createSubcategoria(SubcategoriaDto subcategoriaDto) {
		
		SubcategoriaEntity subcategoriaEntity = getSubcategoriaEntity(subcategoriaDto);
		
		subcategoriaRepository.save(subcategoriaEntity);
		
		return getSubcategoriaResponse(subcategoriaEntity);
		
	}
	
	private SubcategoriaEntity getSubcategoriaEntity(SubcategoriaDto subcategoriaDto) {
		
		if(!subcategoriaRepository.findByNombre(subcategoriaDto.getNombre()).isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subcategoria already exits");

		
		return new SubcategoriaEntity(subcategoriaDto.getNombre());
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
		
		return validSubcategories;
	}
	
	//Assign subcategories to categories
	public List<CategoriaResponse> assignSubcategoriesToCategory(CategoriaSubcategoriaDto catSubDto) {
		var categoryEntity = categoriaRepository.findByNombre(catSubDto.getCategoria());
				
		if(categoryEntity.isEmpty()){
			return new ArrayList<>();
		}

		List<SubcategoriaEntity> validSubcategories = getListSubcategoriaEntity(catSubDto.getSubcategorias());

		validSubcategories.forEach(subcategory -> {
			if(!subcategoriaRepository.existsByCategoriasNombreAndNombre(categoryEntity.get().getNombre(), subcategory.getNombre())) {
				categoryEntity.get().addSubcategoria(subcategory);
				categoriaRepository.save(categoryEntity.get());
			}
		});
		
		List<CategoriaEntity> categoriaEntityList = categoriaRepository.findAll();
		
		return getListCategoriaResponse(categoriaEntityList);
	}
}
