package com.rentfashion.rentappfashion.application.port.out;

import com.rentfashion.rentappfashion.domain.model.prenda.Prenda;

import java.util.List;
import java.util.Optional;

public interface PrendaRepositoryPort {

    Prenda save(Prenda p);
    Optional<Prenda> findByRef(String ref);
    List<Prenda> findByTalla(String talla);
}
