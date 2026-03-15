package com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa;

import com.rentfashion.rentappfashion.application.port.out.EmpleadoRepositoryPort;
import com.rentfashion.rentappfashion.domain.model.Empleado;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.entity.EmpleadoEntity;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.entity.PersonaEntity;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.mapper.EntityMapper;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.repository.EmpleadoJpaRepository;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.repository.PersonaJpaRepository;

import java.util.Optional;

public class EmpleadoRepositoryAdapter implements EmpleadoRepositoryPort {

    private final PersonaJpaRepository personaRepo;
    private final EmpleadoJpaRepository empleadoRepo;
    private final EntityMapper mapper;

    public EmpleadoRepositoryAdapter(PersonaJpaRepository personaRepo, EmpleadoJpaRepository empleadoRepo, EntityMapper mapper) {
        this.personaRepo = personaRepo;
        this.empleadoRepo = empleadoRepo;
        this.mapper = mapper;
    }

    @Override
    public Empleado save(Empleado e) {
        PersonaEntity persona = mapper.toPersonaEntity(e);
        personaRepo.save(persona);

        EmpleadoEntity ee = new EmpleadoEntity();
        ee.setPersona(persona);
        ee.setCargo(e.getCargo());
        EmpleadoEntity saved = empleadoRepo.save(ee);

        return mapper.toEmpleadoDomain(saved);
    }

    @Override
    public Optional<Empleado> findById(String id) {
        return empleadoRepo.findById(id).map(mapper::toEmpleadoDomain);
    }
}
