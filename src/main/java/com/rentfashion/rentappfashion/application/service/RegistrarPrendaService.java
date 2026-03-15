package com.rentfashion.rentappfashion.application.service;

import com.rentfashion.rentappfashion.application.port.in.RegistrarPrendaUseCase;
import com.rentfashion.rentappfashion.application.port.out.PrendaRepositoryPort;
import com.rentfashion.rentappfashion.domain.factory.PrendaFactory;
import com.rentfashion.rentappfashion.domain.model.prenda.Prenda;

import java.util.Map;

public class RegistrarPrendaService  implements RegistrarPrendaUseCase {

    private final PrendaRepositoryPort prendaRepo;
    private final PrendaFactory factory;

    public RegistrarPrendaService(PrendaRepositoryPort prendaRepo, PrendaFactory factory) {
        this.prendaRepo = prendaRepo;
        this.factory = factory;
    }

    @Override
    public Prenda registrar(String tipo, Map<String, Object> datos) {
        Prenda prenda = factory.crearPrenda(tipo, datos);
        return prendaRepo.save(prenda);
    }
}
