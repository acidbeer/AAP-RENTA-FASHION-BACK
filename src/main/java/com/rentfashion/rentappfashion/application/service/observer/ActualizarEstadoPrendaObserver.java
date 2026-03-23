package com.rentfashion.rentappfashion.application.service.observer;

import com.rentfashion.rentappfashion.application.port.out.PrendaRepositoryPort;
import com.rentfashion.rentappfashion.domain.model.prenda.Prenda;

public class ActualizarEstadoPrendaObserver implements AlquilerObserver {

    private final PrendaRepositoryPort prendaRepo;

    public ActualizarEstadoPrendaObserver(PrendaRepositoryPort prendaRepo) {
        this.prendaRepo = prendaRepo;
    }

    @Override
    public void update(AlquilerEvent event) {
        for (Prenda prenda : event.getPrendas()) {
            prenda.setEstado(Prenda.Estado.ALQUILADA);
            prendaRepo.save(prenda);
        }
    }
}
