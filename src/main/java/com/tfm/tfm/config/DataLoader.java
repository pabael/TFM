package com.tfm.tfm.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.tfm.tfm.entity.AutonomousCommunityEntity;
import com.tfm.tfm.entity.PriceEntity;
import com.tfm.tfm.repository.AutonomousCommunityRepository;
import com.tfm.tfm.repository.PriceRepository;

@Component
public class DataLoader {

	@Bean

	CommandLineRunner initDatabase (PriceRepository priceRepository, AutonomousCommunityRepository autonomousCommunityRepository){
		return args -> {

			if(priceRepository.count() == 0){
				priceRepository.save(new PriceEntity(1));
				priceRepository.save(new PriceEntity(2));
				priceRepository.save(new PriceEntity(3));
				priceRepository.save(new PriceEntity(4));
			
			}

			if (autonomousCommunityRepository.count() == 0) {
				autonomousCommunityRepository.save(new AutonomousCommunityEntity("Andalucía"));
				autonomousCommunityRepository.save(new AutonomousCommunityEntity("Aragón"));
				autonomousCommunityRepository.save(new AutonomousCommunityEntity("Asturias"));
				autonomousCommunityRepository.save(new AutonomousCommunityEntity("Islas Baleares"));
				autonomousCommunityRepository.save(new AutonomousCommunityEntity("Canarias"));
				autonomousCommunityRepository.save(new AutonomousCommunityEntity("Cantabria"));
				autonomousCommunityRepository.save(new AutonomousCommunityEntity("Castilla y León"));
				autonomousCommunityRepository.save(new AutonomousCommunityEntity("Castilla-La Mancha"));
				autonomousCommunityRepository.save(new AutonomousCommunityEntity("Cataluña"));
				autonomousCommunityRepository.save(new AutonomousCommunityEntity("Comunidad Valenciana"));
				autonomousCommunityRepository.save(new AutonomousCommunityEntity("Extremadura"));
				autonomousCommunityRepository.save(new AutonomousCommunityEntity("Galicia"));
				autonomousCommunityRepository.save(new AutonomousCommunityEntity("Madrid"));
				autonomousCommunityRepository.save(new AutonomousCommunityEntity("Murcia"));
				autonomousCommunityRepository.save(new AutonomousCommunityEntity("Navarra"));
				autonomousCommunityRepository.save(new AutonomousCommunityEntity("País Vasco"));
				autonomousCommunityRepository.save(new AutonomousCommunityEntity("La Rioja"));
				autonomousCommunityRepository.save(new AutonomousCommunityEntity("Ceuta"));
				autonomousCommunityRepository.save(new AutonomousCommunityEntity("Melilla"));
			};
		};
	}
}
