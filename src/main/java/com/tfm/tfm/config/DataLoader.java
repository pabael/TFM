package com.tfm.tfm.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.tfm.tfm.entity.PriceEntity;
import com.tfm.tfm.repository.PriceRepository;

@Component
public class DataLoader {

	@Bean
	CommandLineRunner initDatabase (PriceRepository priceRepository){
		return args -> {
			if(priceRepository.count() == 0){
				priceRepository.save(new PriceEntity(1));
				priceRepository.save(new PriceEntity(2));
				priceRepository.save(new PriceEntity(3));
				priceRepository.save(new PriceEntity(4));
			}
		};
	}
}
