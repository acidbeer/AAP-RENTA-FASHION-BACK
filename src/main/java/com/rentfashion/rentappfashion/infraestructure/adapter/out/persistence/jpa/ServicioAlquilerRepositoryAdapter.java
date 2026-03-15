package com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa;

import com.rentfashion.rentappfashion.application.port.out.ServicioAlquilerRepositoryPort;
import com.rentfashion.rentappfashion.domain.model.ServicioAlquiler;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.entity.ClienteEntity;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.entity.EmpleadoEntity;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.entity.ServicioAlquilerEntity;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.mapper.EntityMapper;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.repository.ClienteJpaRepository;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.repository.EmpleadoJpaRepository;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.repository.PrendaJpaRepository;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.repository.ServicioAlquilerJpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

public class ServicioAlquilerRepositoryAdapter implements ServicioAlquilerRepositoryPort {

    private final ServicioAlquilerJpaRepository repo;
    private final ClienteJpaRepository clienteRepo;
    private final EmpleadoJpaRepository empleadoRepo;
    private final PrendaJpaRepository prendaRepo;
    private final EntityMapper mapper;

    public ServicioAlquilerRepositoryAdapter(
            ServicioAlquilerJpaRepository repo,
            ClienteJpaRepository clienteRepo,
            EmpleadoJpaRepository empleadoRepo,
            PrendaJpaRepository prendaRepo,
            EntityMapper mapper
    ) {
        this.repo = repo;
        this.clienteRepo = clienteRepo;
        this.empleadoRepo = empleadoRepo;
        this.prendaRepo = prendaRepo;
        this.mapper = mapper;
    }

    @Override
    public ServicioAlquiler save(ServicioAlquiler s) {
        ServicioAlquilerEntity e = new ServicioAlquilerEntity();
        e.setId(s.getNumero());

        ClienteEntity ce = clienteRepo.findById(s.getCliente().getId()).orElseThrow();
        EmpleadoEntity ee = empleadoRepo.findById(s.getEmpleado().getId()).orElseThrow();

        e.setCliente(ce);
        e.setEmpleado(ee);
        e.setFechaSolicitud(s.getFechaSolicitud());
        e.setFechaAlquiler(s.getFechaAlquiler());
        e.setEstado(ServicioAlquilerEntity.Estado.valueOf(s.getEstado().name()));

        var prendasEntities = s.getPrendas().stream()
                .map(p -> prendaRepo.findByRef(p.getRef()).orElseThrow())
                .collect(Collectors.toSet());
        e.setPrendas(prendasEntities);

        ServicioAlquilerEntity saved = repo.save(e);
        return mapper.toServicioDomain(saved);
    }

    @Override
    public Optional<ServicioAlquiler> findByNumero(Long numero) {
        return repo.findById(numero).map(mapper::toServicioDomain);
    }

    @Override
    public java.util.List<ServicioAlquiler> findVigentesPorCliente(String clienteId, LocalDate hoy) {
        return repo.findByCliente_PersonaIdAndFechaAlquilerGreaterThanEqualOrderByFechaAlquilerAsc(clienteId, hoy)
                .stream().map(mapper::toServicioDomain).toList();
    }

    @Override
    public java.util.List<ServicioAlquiler> findByFechaAlquiler(LocalDate fechaAlquiler) {
        return repo.findByFechaAlquiler(fechaAlquiler).stream().map(mapper::toServicioDomain).toList();
    }

    @Override
    public boolean existsPrendaAlquiladaEnFecha(String refPrenda, LocalDate fechaAlquiler) {
        return repo.existsPrendaAlquiladaEnFecha(refPrenda, fechaAlquiler);
    }
}
