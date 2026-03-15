package com.rentfashion.rentappfashion.domain.model;

import com.rentfashion.rentappfashion.domain.model.prenda.Prenda;

import java.time.LocalDateTime;

public class LavanderiaItem {

    public enum Estado { PENDIENTE, ENVIADA }

    private Long id;
    private Prenda prenda;
    private boolean prioridad;
    private Estado estado = Estado.PENDIENTE;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaEnvio;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Prenda getPrenda() { return prenda; }
    public void setPrenda(Prenda prenda) { this.prenda = prenda; }

    public boolean isPrioridad() { return prioridad; }
    public void setPrioridad(boolean prioridad) { this.prioridad = prioridad; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public LocalDateTime getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDateTime fechaEnvio) { this.fechaEnvio = fechaEnvio; }
}
