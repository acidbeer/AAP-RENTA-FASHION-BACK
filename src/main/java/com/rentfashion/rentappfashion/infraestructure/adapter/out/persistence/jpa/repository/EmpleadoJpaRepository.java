package com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.repository;

import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoJpaRepository  extends JpaRepository<EmpleadoEntity, String> {

}
