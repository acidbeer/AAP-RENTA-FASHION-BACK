package com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "servicio_alquiler")
public class ServicioAlquilerEntity {

    public enum Estado { VIGENTE, CERRADO }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // consecutivo

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "empleado_id")
    private EmpleadoEntity empleado;

    @Column(name = "fecha_solicitud", nullable = false)
    private LocalDateTime fechaSolicitud;

    @Column(name = "fecha_alquiler", nullable = false)
    private LocalDate fechaAlquiler;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado = Estado.VIGENTE;

    @ManyToMany
    @JoinTable(
            name = "servicio_alquiler_prenda",
            joinColumns = @JoinColumn(name = "servicio_id"),
            inverseJoinColumns = @JoinColumn(name = "prenda_id")
    )
    private Set<PrendaEntity> prendas = new HashSet<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public ClienteEntity getCliente() { return cliente; }
    public void setCliente(ClienteEntity cliente) { this.cliente = cliente; }

    public EmpleadoEntity getEmpleado() { return empleado; }
    public void setEmpleado(EmpleadoEntity empleado) { this.empleado = empleado; }

    public LocalDateTime getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(LocalDateTime fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }

    public LocalDate getFechaAlquiler() { return fechaAlquiler; }
    public void setFechaAlquiler(LocalDate fechaAlquiler) { this.fechaAlquiler = fechaAlquiler; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public Set<PrendaEntity> getPrendas() { return prendas; }
    public void setPrendas(Set<PrendaEntity> prendas) { this.prendas = prendas; }

}
