package com.rentfashion.rentappfashion.application.service.observer;

import com.rentfashion.rentappfashion.domain.model.prenda.Prenda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlquilerEvent {

    private final Long numeroServicio;
    private final String clienteId;
    private final String empleadoId;
    private final List<Prenda> prendas;

    public AlquilerEvent(Long numeroServicio, String clienteId, String empleadoId, List<Prenda> prendas) {
        this.numeroServicio = numeroServicio;
        this.clienteId = clienteId;
        this.empleadoId = empleadoId;
        this.prendas = prendas == null ? Collections.emptyList() : new ArrayList<>(prendas);
    }

    public Long getNumeroServicio() {
        return numeroServicio;
    }

    public String getClienteId() {
        return clienteId;
    }

    public String getEmpleadoId() {
        return empleadoId;
    }

    public List<Prenda> getPrendas() {
        return Collections.unmodifiableList(prendas);
    }
}
