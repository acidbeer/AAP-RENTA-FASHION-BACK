package com.rentfashion.rentappfashion.infraestructure.adapter.in.web;

import com.rentfashion.rentappfashion.application.port.in.RegistrarEmpleadoUseCase;
import com.rentfashion.rentappfashion.domain.model.Empleado;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final RegistrarEmpleadoUseCase registrarEmpleado;

    public EmpleadoController(RegistrarEmpleadoUseCase registrarEmpleado) {
        this.registrarEmpleado = registrarEmpleado;
    }

    @PostMapping
    public ResponseEntity<Empleado> registrar(@RequestBody Empleado empleado) {
        return ResponseEntity.ok(registrarEmpleado.registrar(empleado));
    }
}
