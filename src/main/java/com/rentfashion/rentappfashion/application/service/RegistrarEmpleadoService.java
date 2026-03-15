package com.rentfashion.rentappfashion.application.service;

import com.rentfashion.rentappfashion.application.port.in.RegistrarEmpleadoUseCase;
import com.rentfashion.rentappfashion.application.port.out.EmpleadoRepositoryPort;
import com.rentfashion.rentappfashion.domain.exception.ValidationException;
import com.rentfashion.rentappfashion.domain.model.Empleado;

public class RegistrarEmpleadoService implements RegistrarEmpleadoUseCase {

    private final EmpleadoRepositoryPort repo;

    public RegistrarEmpleadoService(EmpleadoRepositoryPort repo) {
        this.repo = repo;
    }

    @Override
    public Empleado registrar(Empleado empleado) {
        if (empleado == null) throw new ValidationException("empleado es obligatorio");
        if (empleado.getId() == null || empleado.getId().isBlank()) throw new ValidationException("id es obligatorio");
        if (empleado.getCargo() == null || empleado.getCargo().isBlank()) throw new ValidationException("cargo es obligatorio");
        return repo.save(empleado);
    }
}
