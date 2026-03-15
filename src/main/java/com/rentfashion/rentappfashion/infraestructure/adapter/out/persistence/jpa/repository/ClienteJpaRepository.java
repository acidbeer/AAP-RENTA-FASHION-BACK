package com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.repository;

import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, String> {

}
