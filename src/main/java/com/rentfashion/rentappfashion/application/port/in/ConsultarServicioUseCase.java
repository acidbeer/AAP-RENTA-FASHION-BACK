package com.rentfashion.rentappfashion.application.port.in;

import com.rentfashion.rentappfashion.domain.model.ServicioAlquiler;

public interface ConsultarServicioUseCase {

    ServicioAlquiler consultarPorNumero(Long numero);

}
