package com.rentfashion.rentappfashion.application.port.in;

import com.rentfashion.rentappfashion.domain.model.Empleado;

public interface RegistrarEmpleadoUseCase {

    Empleado registrar(Empleado empleado);
}
