package com.tfm.tfm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfm.tfm.entity.LocationEntity;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, String>{
    boolean existsByName(String name);
    
    Optional<LocationEntity> findByName(String name);
}
