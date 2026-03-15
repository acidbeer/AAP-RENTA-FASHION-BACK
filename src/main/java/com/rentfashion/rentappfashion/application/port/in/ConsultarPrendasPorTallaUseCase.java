package com.rentfashion.rentappfashion.application.port.in;

import com.rentfashion.rentappfashion.domain.model.prenda.Prenda;

import java.util.List;
import java.util.Map;

public interface ConsultarPrendasPorTallaUseCase {

    Map<String, List<Prenda>> consultarPorTallaSeparadasPorTipo(String talla);
}
