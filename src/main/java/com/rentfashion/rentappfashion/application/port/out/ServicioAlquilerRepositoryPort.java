package com.rentfashion.rentappfashion.application.port.out;

import com.rentfashion.rentappfashion.domain.model.ServicioAlquiler;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ServicioAlquilerRepositoryPort {

    ServicioAlquiler save(ServicioAlquiler s);
    Optional<ServicioAlquiler> findByNumero(Long numero);

    List<ServicioAlquiler> findVigentesPorCliente(String clienteId, LocalDate hoy);
    List<ServicioAlquiler> findByFechaAlquiler(LocalDate fechaAlquiler);

    boolean existsPrendaAlquiladaEnFecha(String refPrenda, LocalDate fechaAlquiler);
}
