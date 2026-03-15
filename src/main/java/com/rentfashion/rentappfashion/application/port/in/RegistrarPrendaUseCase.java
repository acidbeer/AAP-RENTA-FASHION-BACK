package com.rentfashion.rentappfashion.application.port.in;

import com.rentfashion.rentappfashion.domain.model.prenda.Prenda;

import java.util.Map;

public interface RegistrarPrendaUseCase {
    Prenda registrar(String tipo, Map<String, Object> datos);
}
