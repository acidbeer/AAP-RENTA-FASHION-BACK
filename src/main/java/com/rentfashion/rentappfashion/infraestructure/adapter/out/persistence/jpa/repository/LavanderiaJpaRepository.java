package com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.repository;

import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.entity.LavanderiaItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LavanderiaJpaRepository extends JpaRepository<LavanderiaItemEntity, Long> {

    List<LavanderiaItemEntity> findByEstadoOrderByPrioridadDescFechaRegistroAsc(LavanderiaItemEntity.Estado estado);
}
