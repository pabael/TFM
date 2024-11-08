package com.tfm.tfm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfm.tfm.dto.MarcaDto;
import com.tfm.tfm.entity.CategoriaEntity;
import com.tfm.tfm.entity.MarcaEntity;
import com.tfm.tfm.entity.SubcategoriaEntity;
import com.tfm.tfm.repository.CategoriaRepository;
import com.tfm.tfm.repository.MarcaRepository;
import com.tfm.tfm.repository.SubcategoriaRepository;
import com.tfm.tfm.response.MarcaResponse;

@Service
public class MarcaServiceImpl implements MarcaService{

	@Autowired private MarcaRepository marcaRepository;
	@Autowired private CategoriaRepository categoriaRepository;
	@Autowired private SubcategoriaRepository subcategoriaRepository;
	
	@Autowired private CategoriaSubcategoriaService categoriaSubcategoriaService;

	public MarcaResponse createMarca(MarcaDto marcaDto) {
		
		MarcaEntity marcaEntity = getMarcaEntity(marcaDto);
		
		marcaRepository.save(marcaEntity);
		
		return getMarcaResponse(marcaEntity);
		
	}
	
	private MarcaEntity getMarcaEntity(MarcaDto marcaDto) {
		
		return new MarcaEntity(
				marcaDto.getNombre(), 
				marcaDto.getResumen(),
				marcaDto.getMateriales(),
				marcaDto.isCrueltyFree(),
				marcaDto.isVegano(),
				marcaDto.getCompromiso(),
				marcaDto.getProcesoProduccion(),
				categoriaSubcategoriaService.getListCategoriaEntity(marcaDto.getCategorias()),
				categoriaSubcategoriaService.getListSubcategoriaEntity(marcaDto.getSubcategorias())
				);
	}
	
	private MarcaResponse getMarcaResponse(MarcaEntity marcaEntity) {
		List<String> categories = new ArrayList<>();
		marcaEntity.getCategorias().forEach(category -> {
			categories.add(category.getNombre());
		});
		
		List<String> subcategories = new ArrayList<>();
		marcaEntity.getSubcategorias().forEach(subcategory -> {
			subcategories.add(subcategory.getNombre());
		});
		
		return new MarcaResponse(
				marcaEntity.getNombre(), 
				marcaEntity.getResumen(),
				marcaEntity.getMateriales(),
				marcaEntity.isCrueltyFree(),
				marcaEntity.isVegano(),
				marcaEntity.getCompromiso(),
				marcaEntity.getProcesoProduccion(),
				categories,
				subcategories
				);
	}
}
