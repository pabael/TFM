package com.tfm.tfm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfm.tfm.entity.SubcategoriaEntity;

@Repository
public interface SubcategoriaRepository extends JpaRepository<SubcategoriaEntity, String>{
    boolean existsByNombre(String nombre);
    
    Optional<SubcategoriaEntity> findByNombre(String nombre);
}
