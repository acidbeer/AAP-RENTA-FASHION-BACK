package com.rentfashion.rentappfashion.application.service;

import com.rentfashion.rentappfashion.application.port.in.ConsultarPrendasPorTallaUseCase;
import com.rentfashion.rentappfashion.application.port.in.ConsultarServicioUseCase;
import com.rentfashion.rentappfashion.application.port.in.ConsultarServiciosPorClienteUseCase;
import com.rentfashion.rentappfashion.application.port.in.ConsultarServiciosPorFechaUseCase;
import com.rentfashion.rentappfashion.application.port.out.PrendaRepositoryPort;
import com.rentfashion.rentappfashion.application.port.out.ServicioAlquilerRepositoryPort;
import com.rentfashion.rentappfashion.domain.exception.NotFoundException;
import com.rentfashion.rentappfashion.domain.model.ServicioAlquiler;
import com.rentfashion.rentappfashion.domain.model.prenda.Prenda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ConsultasServicioService implements
        ConsultarServicioUseCase,
        ConsultarServiciosPorClienteUseCase,
        ConsultarServiciosPorFechaUseCase,
        ConsultarPrendasPorTallaUseCase {

    private final ServicioAlquilerRepositoryPort servicioRepo;
    private final PrendaRepositoryPort prendaRepo;

    public ConsultasServicioService(ServicioAlquilerRepositoryPort servicioRepo, PrendaRepositoryPort prendaRepo) {
        this.servicioRepo = servicioRepo;
        this.prendaRepo = prendaRepo;
    }

    @Override
    public ServicioAlquiler consultarPorNumero(Long numero) {
        return servicioRepo.findByNumero(numero)
                .orElseThrow(() -> new NotFoundException("Servicio no existe: " + numero));
    }

    @Override
    public List<ServicioAlquiler> consultarVigentes(String clienteId) {
        return servicioRepo.findVigentesPorCliente(clienteId, LocalDate.now());
    }

    @Override
    public List<ServicioAlquiler> consultarPorFecha(LocalDate fechaAlquiler) {
        return servicioRepo.findByFechaAlquiler(fechaAlquiler);
    }

    @Override
    public Map<String, List<Prenda>> consultarPorTallaSeparadasPorTipo(String talla) {
        List<Prenda> prendas = prendaRepo.findByTalla(talla);
        Map<String, List<Prenda>> res = new LinkedHashMap<>();
        for (Prenda p : prendas) {
            res.computeIfAbsent(p.getTipo().name(), k -> new ArrayList<>()).add(p);
        }
        return res;
    }
}
