package com.rentfashion.rentappfashion.application.service.command;

import com.rentfashion.rentappfashion.application.port.in.RegistrarServicioAlquilerUseCase;

import java.time.LocalDate;
import java.util.List;

public class RegistrarServicioAlquilerCommand implements Command{

    private final RegistrarServicioAlquilerUseCase registrarServicio;
    private final String clienteId;
    private final String empleadoId;
    private final List<String> refsPrenda;
    private final LocalDate fechaAlquiler;

    public RegistrarServicioAlquilerCommand(
            RegistrarServicioAlquilerUseCase registrarServicio,
            String clienteId,
            String empleadoId,
            List<String> refsPrenda,
            LocalDate fechaAlquiler
    ) {
        this.registrarServicio = registrarServicio;
        this.clienteId = clienteId;
        this.empleadoId = empleadoId;
        this.refsPrenda = refsPrenda;
        this.fechaAlquiler = fechaAlquiler;
    }

    @Override
    public Long execute() {
        return registrarServicio.registrarServicio(clienteId, empleadoId, refsPrenda, fechaAlquiler);
    }
}
