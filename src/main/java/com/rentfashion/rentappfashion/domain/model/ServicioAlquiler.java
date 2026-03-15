package com.rentfashion.rentappfashion.domain.model;

import com.rentfashion.rentappfashion.domain.model.prenda.Prenda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServicioAlquiler {

    public enum Estado { VIGENTE, CERRADO }

    private Long numero;
    private Cliente cliente;
    private Empleado empleado;
    private LocalDateTime fechaSolicitud;
    private LocalDate fechaAlquiler;
    private Estado estado = Estado.VIGENTE;
    private List<Prenda> prendas = new ArrayList<>();

    public Long getNumero() { return numero; }
    public void setNumero(Long numero) { this.numero = numero; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Empleado getEmpleado() { return empleado; }
    public void setEmpleado(Empleado empleado) { this.empleado = empleado; }

    public LocalDateTime getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(LocalDateTime fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }

    public LocalDate getFechaAlquiler() { return fechaAlquiler; }
    public void setFechaAlquiler(LocalDate fechaAlquiler) { this.fechaAlquiler = fechaAlquiler; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public List<Prenda> getPrendas() { return prendas; }
    public void setPrendas(List<Prenda> prendas) { this.prendas = prendas; }
}
