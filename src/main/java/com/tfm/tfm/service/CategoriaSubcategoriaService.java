package com.tfm.tfm.service;

import java.util.List;

import com.tfm.tfm.dto.CategoriaDto;
import com.tfm.tfm.dto.CategoriaSubcategoriaDto;
import com.tfm.tfm.dto.SubcategoriaDto;
import com.tfm.tfm.entity.CategoriaEntity;
import com.tfm.tfm.entity.SubcategoriaEntity;
import com.tfm.tfm.response.CategoriaResponse;
import com.tfm.tfm.response.SubcategoriaResponse;

public interface CategoriaSubcategoriaService {
		
	CategoriaResponse createCategoria(CategoriaDto categoriaDto);
	SubcategoriaResponse createSubcategoria(SubcategoriaDto subcategoriaDto);

	public List<CategoriaEntity> getListCategoriaEntity(List<String> categories);
	public List<SubcategoriaEntity> getListSubcategoriaEntity(List<String> subcategories);

	public List<CategoriaResponse> assignSubcategoriesToCategory(CategoriaSubcategoriaDto catSubDto);

	
}
