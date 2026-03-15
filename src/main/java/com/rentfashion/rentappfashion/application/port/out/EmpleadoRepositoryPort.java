package com.rentfashion.rentappfashion.application.port.out;

import com.rentfashion.rentappfashion.domain.model.Empleado;

import java.util.Optional;

public interface EmpleadoRepositoryPort {

    Empleado save(Empleado e);
    Optional<Empleado> findById(String id);
}
