package com.rentfashion.rentappfashion.application.port.out;

import com.rentfashion.rentappfashion.domain.model.Cliente;

import java.util.Optional;

public interface ClienteRepositoryPort {

    Cliente save(Cliente c);
    Optional<Cliente> findById(String id);
}
