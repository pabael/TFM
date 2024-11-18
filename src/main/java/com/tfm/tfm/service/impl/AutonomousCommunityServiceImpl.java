package com.tfm.tfm.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tfm.tfm.entity.AutonomousCommunityEntity;
import com.tfm.tfm.repository.AutonomousCommunityRepository;
import com.tfm.tfm.service.AutonomousCommunityService;

@Service
public class AutonomousCommunityServiceImpl implements AutonomousCommunityService{

	@Autowired private AutonomousCommunityRepository autonomousCommunityRepository;

	public AutonomousCommunityEntity getAutonomousCommunityEntity(String region_code) {
		
		String name = getName(region_code);

		Optional <AutonomousCommunityEntity> autonomousCommunityEntity = autonomousCommunityRepository.findByName(name);

		//This should not happend
		if(autonomousCommunityEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Autonomous Community dos not exist");
		
		return autonomousCommunityEntity.get();
	}

	private String getName(String region_code){
		String autonomousCommunity = "";
		switch (region_code) {
				case "AN":
						autonomousCommunity = "Andalucía";
						break;
				case "AR":
						autonomousCommunity = "Aragón";
						break;
				case "AS":
						autonomousCommunity = "Asturias";
						break;
				case "PM":
						autonomousCommunity = "Islas Baleares";
						break;
				case "CN":
						autonomousCommunity = "Canarias";
						break;
				case "CB":
						autonomousCommunity = "Cantabria";
						break;
				case "CL":
						autonomousCommunity = "Castilla y León";
						break;
				case "CM":
						autonomousCommunity = "Castilla-La Mancha";
						break;
				case "CT":
						autonomousCommunity = "Cataluña";
						break;
				case "VC":
						autonomousCommunity = "Comunidad Valenciana";
						break;
				case "EX":
						autonomousCommunity = "Extremadura";
						break;
				case "GA":
						autonomousCommunity = "Galicia";
						break;
				case "MD":
						autonomousCommunity = "Madrid";
						break;
				case "MC":
						autonomousCommunity = "Murcia";
						break;
				case "NC":
						autonomousCommunity = "Navarra";
						break;
				case "PV":
						autonomousCommunity = "País Vasco";
						break;
				case "LO":
						autonomousCommunity = "La Rioja";
						break;
				case "CE":
						autonomousCommunity = "Ceuta";
						break;
				case "ML":
						autonomousCommunity = "Melilla";
						break;
				default:
						autonomousCommunity = "Código no válido";
						break;
		}
			
		return autonomousCommunity;
	}
}
