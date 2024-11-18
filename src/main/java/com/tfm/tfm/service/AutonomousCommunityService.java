package com.tfm.tfm.service;

import java.util.List;

import com.tfm.tfm.dto.AutonomousCommunityDto;
import com.tfm.tfm.entity.AutonomousCommunityEntity;
import com.tfm.tfm.response.AutonomousCommunityResponse;

public interface AutonomousCommunityService {

	List<AutonomousCommunityResponse> getAutonomousCommunityList();

	List<AutonomousCommunityEntity> getAutonomousCommunityEntityList ();

	AutonomousCommunityEntity getAutonomousCommunityEntity(String name);

}
