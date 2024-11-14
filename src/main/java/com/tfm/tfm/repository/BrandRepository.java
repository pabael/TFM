package com.tfm.tfm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfm.tfm.entity.BrandEntity;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, String>{
	
	Optional<BrandEntity> findByName(String name);
}