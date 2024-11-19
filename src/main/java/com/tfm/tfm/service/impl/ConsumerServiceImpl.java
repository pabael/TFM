package com.tfm.tfm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tfm.tfm.dto.ConsumerDto;
import com.tfm.tfm.entity.BrandEntity;
import com.tfm.tfm.entity.ConsumerEntity;
import com.tfm.tfm.repository.ConsumerRepository;
import com.tfm.tfm.response.ConsumerResponse;
import com.tfm.tfm.service.ConsumerService;
import com.tfm.tfm.service.GeneralService;


@Service
public class ConsumerServiceImpl implements ConsumerService{

	@Autowired private ConsumerRepository consumerRepository;

	@Autowired private GeneralService generalService;

	public ConsumerResponse createConsumer(ConsumerDto consumerDto) {
		
		ConsumerEntity consumerEntity = getConsumerEntity(consumerDto);
		
		consumerRepository.save(consumerEntity);
		
		return getConsumerResponse(consumerEntity);
		
	}
	
	private ConsumerEntity getConsumerEntity(ConsumerDto consumerDto) {
		
		if(!consumerRepository.findByType(consumerDto.getType()).isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Consumer already exists");
		
		return new ConsumerEntity(generalService.capitalizeFirstLetter(consumerDto.getType()));
	}
	
	private ConsumerResponse getConsumerResponse(ConsumerEntity consumerEntity) {
		return new ConsumerResponse(consumerEntity.getType());
	}


	public List<ConsumerEntity> getListConsumerEntity(List<String> consumers) {
		List<ConsumerEntity> validConsumers = new ArrayList<>();

		consumers.forEach(consumer -> {
			var consumerEntity = consumerRepository.findByType(consumer);
			if(consumerEntity.isPresent()) 
				validConsumers.add(consumerEntity.get());
		});
		
		return validConsumers;
	}

	public	void deleteConsumer(ConsumerDto consumerDto){
	
		Optional<ConsumerEntity> consumer = consumerRepository.findByType(consumerDto.getType());
		
		if(consumer.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Consumer does not exist");
		
		consumerRepository.delete(consumer.get());
	}

	public	List<BrandEntity> getBrandsByConsumer(String consumer){
		Optional<ConsumerEntity> consumerEntity = consumerRepository.findByType(consumer);

		if(consumerEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Consumer does not exist");

		return consumerEntity.get().getBrands();
	}


}
