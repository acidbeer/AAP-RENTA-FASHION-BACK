package com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa;

import com.rentfashion.rentappfashion.application.port.out.LavanderiaRepositoryPort;
import com.rentfashion.rentappfashion.domain.model.LavanderiaItem;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.entity.LavanderiaItemEntity;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.entity.PrendaEntity;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.mapper.EntityMapper;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.repository.LavanderiaJpaRepository;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.repository.PrendaJpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public class LavanderiaRepositoryAdapter implements LavanderiaRepositoryPort {

    private final LavanderiaJpaRepository repo;
    private final PrendaJpaRepository prendaRepo;
    private final EntityMapper mapper;

    public LavanderiaRepositoryAdapter(LavanderiaJpaRepository repo, PrendaJpaRepository prendaRepo, EntityMapper mapper) {
        this.repo = repo;
        this.prendaRepo = prendaRepo;
        this.mapper = mapper;
    }

    @Override
    public LavanderiaItem save(LavanderiaItem item) {
        LavanderiaItemEntity e = new LavanderiaItemEntity();
        e.setId(item.getId());
        PrendaEntity pe = prendaRepo.findByRef(item.getPrenda().getRef()).orElseThrow();
        e.setPrenda(pe);
        e.setPrioridad(item.isPrioridad());
        e.setEstado(LavanderiaItemEntity.Estado.valueOf(item.getEstado().name()));
        e.setFechaRegistro(item.getFechaRegistro() == null ? LocalDateTime.now() : item.getFechaRegistro());
        e.setFechaEnvio(item.getFechaEnvio());
        return mapper.toLavanderiaDomain(repo.save(e));
    }

    @Override
    public List<LavanderiaItem> findPendientes() {
        return repo.findByEstadoOrderByPrioridadDescFechaRegistroAsc(LavanderiaItemEntity.Estado.PENDIENTE)
                .stream().map(mapper::toLavanderiaDomain).toList();
    }

    @Override
    public List<LavanderiaItem> takePendientesOrdenados(int cantidad) {
        var all = repo.findByEstadoOrderByPrioridadDescFechaRegistroAsc(LavanderiaItemEntity.Estado.PENDIENTE);
        return all.stream().limit(cantidad).map(mapper::toLavanderiaDomain).toList();
    }

    @Override
    public LavanderiaItem markEnviada(LavanderiaItem item) {
        LavanderiaItemEntity e = repo.findById(item.getId()).orElseThrow();
        e.setEstado(LavanderiaItemEntity.Estado.ENVIADA);
        e.setFechaEnvio(LocalDateTime.now());
        return mapper.toLavanderiaDomain(repo.save(e));
    }
}
