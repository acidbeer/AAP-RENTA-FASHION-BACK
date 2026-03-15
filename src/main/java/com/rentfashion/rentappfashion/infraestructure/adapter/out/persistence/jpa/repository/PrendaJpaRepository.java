package com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.repository;

import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.entity.PrendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrendaJpaRepository extends JpaRepository<PrendaEntity, Long> {

    Optional<PrendaEntity> findByRef(String ref);
    List<PrendaEntity> findByTalla(String talla);

}
