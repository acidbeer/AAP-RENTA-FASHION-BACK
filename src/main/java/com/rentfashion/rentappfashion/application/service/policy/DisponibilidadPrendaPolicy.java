package com.rentfashion.rentappfashion.application.service.policy;

import com.rentfashion.rentappfashion.application.port.out.ServicioAlquilerRepositoryPort;
import com.rentfashion.rentappfashion.domain.exception.BusinessRuleException;

import java.time.LocalDate;

public class DisponibilidadPrendaPolicy {

    private final ServicioAlquilerRepositoryPort servicioRepo;

    public DisponibilidadPrendaPolicy(ServicioAlquilerRepositoryPort servicioRepo) {
        this.servicioRepo = servicioRepo;
    }

    public void asegurarDisponible(String refPrenda, LocalDate fechaAlquiler) {
        boolean yaAlquiladaEseDia = servicioRepo.existsPrendaAlquiladaEnFecha(refPrenda, fechaAlquiler);
        if (yaAlquiladaEseDia) {
            throw new BusinessRuleException("La prenda " + refPrenda + " no está disponible para la fecha " + fechaAlquiler);
        }
    }

}
