package com.rentfashion.rentappfashion.application.service.strategy;

import com.rentfashion.rentappfashion.domain.exception.ValidationException;
import com.rentfashion.rentappfashion.domain.model.ServicioAlquiler;
import com.rentfashion.rentappfashion.domain.model.prenda.Prenda;

import java.time.LocalDate;
import java.util.List;

public class ProcesamientoAlquilerBasicoStrategy implements ProcesamientoAlquilerStrategy {

    @Override
    public void procesar(ServicioAlquiler servicio) {
        if (servicio == null) {
            throw new ValidationException("El servicio de alquiler no puede ser nulo");
        }

        if (servicio.getPrendas() == null || servicio.getPrendas().isEmpty()) {
            throw new ValidationException("El servicio debe contener al menos una prenda");
        }

        if (servicio.getFechaAlquiler() == null) {
            throw new ValidationException("La fecha de alquiler es obligatoria");
        }

        validarPrendas(servicio.getPrendas());
        validarFechaAlquiler(servicio.getFechaAlquiler());

        // Punto de extensión para futuras reglas:
        // - calcular total
        // - calcular fecha estimada de devolución
        // - asignar observaciones
        // - aplicar descuentos
        // - validar máximo de prendas por alquiler
    }

    private void validarPrendas(List<Prenda> prendas) {
        for (Prenda prenda : prendas) {
            if (prenda == null) {
                throw new ValidationException("No se permiten prendas nulas en el servicio");
            }

            if (prenda.getRef() == null || prenda.getRef().isBlank()) {
                throw new ValidationException("Todas las prendas deben tener una referencia válida");
            }
        }
    }

    private void validarFechaAlquiler(LocalDate fechaAlquiler) {
        if (fechaAlquiler.isBefore(LocalDate.now())) {
            throw new ValidationException("La fecha de alquiler no puede ser anterior a la fecha actual");
        }

    }
}
