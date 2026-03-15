package com.rentfashion.rentappfashion.application.port.in;

import com.rentfashion.rentappfashion.domain.model.ServicioAlquiler;

import java.util.List;

public interface ConsultarServiciosPorClienteUseCase {

    List<ServicioAlquiler> consultarVigentes(String clienteId);

}
