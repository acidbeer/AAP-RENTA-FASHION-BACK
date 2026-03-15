package com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa;

import com.rentfashion.rentappfashion.application.port.out.PrendaRepositoryPort;
import com.rentfashion.rentappfashion.domain.model.prenda.Prenda;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.entity.PrendaEntity;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.mapper.EntityMapper;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.repository.PrendaJpaRepository;

import java.util.List;
import java.util.Optional;

public class PrendaRepositoryAdapter implements PrendaRepositoryPort {

    private final PrendaJpaRepository repo;
    private final EntityMapper mapper;

    public PrendaRepositoryAdapter(PrendaJpaRepository repo, EntityMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public Prenda save(Prenda p) {
        PrendaEntity saved = repo.save(mapper.toPrendaEntity(p));
        return mapper.toPrendaDomain(saved);
    }

    @Override
    public Optional<Prenda> findByRef(String ref) {
        return repo.findByRef(ref).map(mapper::toPrendaDomain);
    }

    @Override
    public List<Prenda> findByTalla(String talla) {
        return repo.findByTalla(talla).stream().map(mapper::toPrendaDomain).toList();
    }
}
