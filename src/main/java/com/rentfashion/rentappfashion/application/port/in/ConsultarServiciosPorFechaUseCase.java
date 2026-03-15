package com.rentfashion.rentappfashion.application.port.in;

import com.rentfashion.rentappfashion.domain.model.ServicioAlquiler;

import java.time.LocalDate;
import java.util.List;

public interface ConsultarServiciosPorFechaUseCase {

    List<ServicioAlquiler> consultarPorFecha(LocalDate fechaAlquiler);



}
