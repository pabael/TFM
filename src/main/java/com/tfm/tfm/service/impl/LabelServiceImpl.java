package com.tfm.tfm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tfm.tfm.dto.LabelDto;
import com.tfm.tfm.entity.LabelEntity;
import com.tfm.tfm.repository.LabelRepository;
import com.tfm.tfm.response.LabelResponse;
import com.tfm.tfm.service.GeneralService;
import com.tfm.tfm.service.LabelService;

@Service
public class LabelServiceImpl implements LabelService{

	@Autowired private LabelRepository labelRepository;

	@Autowired private GeneralService generalService;

	public LabelResponse createLabel(LabelDto labelDto) {
		
		LabelEntity labelEntity = getLabelEntity(labelDto);
		
		labelRepository.save(labelEntity);
		
		return getLabelResponse(labelEntity);
		
	}
	
	private LabelEntity getLabelEntity(LabelDto labelDto) {
		
		if(labelRepository.findByName(labelDto.getName()).isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "label already exists");
		
		return new LabelEntity(generalService.capitalizeFirstLetter(labelDto.getName()));
	}
	
	private LabelResponse getLabelResponse(LabelEntity labelEntity) {
		return new LabelResponse(labelEntity.getName());
	}


	public List<LabelEntity> getListLabelEntity(List<String> labels) {
		List<LabelEntity> validLabels = new ArrayList<>();

		labels.forEach(label -> {
			var labelEntity = labelRepository.findByName(label);
			if(labelEntity.isPresent()) 
				validLabels.add(labelEntity.get());
		});
		
		return validLabels;
	}

	public	void deleteLabel(LabelDto labelDto){
	
		Optional<LabelEntity> label = labelRepository.findByName(labelDto.getName());
		
		if(label.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Label does not exist");
		
		labelRepository.delete(label.get());
	}

}
