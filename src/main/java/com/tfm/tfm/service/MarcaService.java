package com.tfm.tfm.service;

import com.tfm.tfm.dto.MarcaDto;
import com.tfm.tfm.response.MarcaResponse;

public interface MarcaService {
		
	MarcaResponse createMarca(MarcaDto marcaDto);
}
