package com.rentfashion.rentappfashion.application.service;

import com.rentfashion.rentappfashion.application.port.in.RegistrarClienteUseCase;
import com.rentfashion.rentappfashion.application.port.out.ClienteRepositoryPort;
import com.rentfashion.rentappfashion.domain.exception.ValidationException;
import com.rentfashion.rentappfashion.domain.model.Cliente;

public class RegistrarClienteService implements RegistrarClienteUseCase {

    private final ClienteRepositoryPort repo;

    public RegistrarClienteService(ClienteRepositoryPort repo) {
        this.repo = repo;
    }

    @Override
    public Cliente registrar(Cliente cliente) {
        if (cliente == null) throw new ValidationException("cliente es obligatorio");
        if (cliente.getId() == null || cliente.getId().isBlank()) throw new ValidationException("id es obligatorio");
        if (cliente.getMail() == null || cliente.getMail().isBlank()) throw new ValidationException("mail es obligatorio");
        return repo.save(cliente);
    }
}
