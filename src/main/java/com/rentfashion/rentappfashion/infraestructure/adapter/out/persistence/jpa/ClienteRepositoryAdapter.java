package com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa;

import com.rentfashion.rentappfashion.application.port.out.ClienteRepositoryPort;
import com.rentfashion.rentappfashion.domain.model.Cliente;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.entity.ClienteEntity;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.entity.PersonaEntity;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.mapper.EntityMapper;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.repository.ClienteJpaRepository;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.repository.PersonaJpaRepository;

import java.util.Optional;

public class ClienteRepositoryAdapter implements ClienteRepositoryPort {

    private final PersonaJpaRepository personaRepo;
    private final ClienteJpaRepository clienteRepo;
    private final EntityMapper mapper;

    public ClienteRepositoryAdapter(PersonaJpaRepository personaRepo, ClienteJpaRepository clienteRepo, EntityMapper mapper) {
        this.personaRepo = personaRepo;
        this.clienteRepo = clienteRepo;
        this.mapper = mapper;
    }

    @Override
    public Cliente save(Cliente c) {
        PersonaEntity persona = mapper.toPersonaEntity(c);
        personaRepo.save(persona);

        ClienteEntity ce = new ClienteEntity();
        ce.setPersona(persona);
        ce.setMail(c.getMail());
        ClienteEntity saved = clienteRepo.save(ce);

        return mapper.toClienteDomain(saved);
    }

    @Override
    public Optional<Cliente> findById(String id) {
        return clienteRepo.findById(id).map(mapper::toClienteDomain);
    }
}
