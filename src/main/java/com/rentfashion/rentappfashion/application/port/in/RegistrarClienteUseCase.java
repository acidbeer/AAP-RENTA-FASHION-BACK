package com.rentfashion.rentappfashion.application.port.in;

import com.rentfashion.rentappfashion.domain.model.Cliente;

public interface RegistrarClienteUseCase {

    Cliente registrar(Cliente cliente);
}
