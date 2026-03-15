package com.rentfashion.rentappfashion.application.port.in;

import java.time.LocalDate;
import java.util.List;

public interface RegistrarServicioAlquilerUseCase {

    Long registrarServicio(String clienteId, String empleadoId, List<String> refsPrenda, LocalDate fechaAlquiler);
}
