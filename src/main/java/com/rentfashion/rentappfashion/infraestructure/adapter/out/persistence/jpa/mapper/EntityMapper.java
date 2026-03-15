package com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.mapper;

import com.rentfashion.rentappfashion.domain.model.Cliente;
import com.rentfashion.rentappfashion.domain.model.Empleado;
import com.rentfashion.rentappfashion.domain.model.LavanderiaItem;
import com.rentfashion.rentappfashion.domain.model.Persona;
import com.rentfashion.rentappfashion.domain.model.ServicioAlquiler;
import com.rentfashion.rentappfashion.domain.model.prenda.Disfraz;
import com.rentfashion.rentappfashion.domain.model.prenda.Prenda;
import com.rentfashion.rentappfashion.domain.model.prenda.TrajeCaballero;
import com.rentfashion.rentappfashion.domain.model.prenda.VestidoDama;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.entity.ClienteEntity;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.entity.EmpleadoEntity;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.entity.LavanderiaItemEntity;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.entity.PersonaEntity;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.entity.PrendaEntity;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.entity.ServicioAlquilerEntity;

import java.util.stream.Collectors;

public class EntityMapper {

    // ===== Persona/Cliente/Empleado =====
    public PersonaEntity toPersonaEntity(Persona p) {
        PersonaEntity e = new PersonaEntity();
        e.setId(p.getId());
        e.setNombre(p.getNombre());
        e.setDireccion(p.getDireccion());
        e.setTelefono(p.getTelefono());
        return e;
    }

    public Cliente toClienteDomain(ClienteEntity e) {
        PersonaEntity p = e.getPersona();
        return new Cliente(p.getId(), p.getNombre(), p.getDireccion(), p.getTelefono(), e.getMail());
    }

    public Empleado toEmpleadoDomain(EmpleadoEntity e) {
        PersonaEntity p = e.getPersona();
        return new Empleado(p.getId(), p.getNombre(), p.getDireccion(), p.getTelefono(), e.getCargo());
    }

    // ===== Prenda =====
    public Prenda toPrendaDomain(PrendaEntity e) {
        Prenda.Estado estado = Prenda.Estado.valueOf(e.getEstado().name());
        return switch (e.getTipo()) {
            case VESTIDO_DAMA -> new VestidoDama(
                    e.getId(), e.getRef(), e.getColor(), e.getMarca(), e.getTalla(), e.getValorAlquiler(),
                    estado,
                    Boolean.TRUE.equals(e.getPedreria()),
                    e.getAltura(),
                    e.getCantPiezas() == null ? 0 : e.getCantPiezas()
            );
            case TRAJE_CABALLERO -> new TrajeCaballero(
                    e.getId(), e.getRef(), e.getColor(), e.getMarca(), e.getTalla(), e.getValorAlquiler(),
                    estado,
                    e.getTipoTraje(),
                    e.getAderezo()
            );
            case DISFRAZ -> new Disfraz(
                    e.getId(), e.getRef(), e.getColor(), e.getMarca(), e.getTalla(), e.getValorAlquiler(),
                    estado,
                    e.getNombreDisfraz()
            );
        };
    }

    public PrendaEntity toPrendaEntity(Prenda p) {
        PrendaEntity e = new PrendaEntity();
        e.setId(p.getId());
        e.setRef(p.getRef());
        e.setColor(p.getColor());
        e.setMarca(p.getMarca());
        e.setTalla(p.getTalla());
        e.setValorAlquiler(p.getValorAlquiler());
        e.setEstado(PrendaEntity.Estado.valueOf(p.getEstado().name()));

        if (p instanceof VestidoDama vd) {
            e.setTipo(PrendaEntity.Tipo.VESTIDO_DAMA);
            e.setPedreria(vd.isPedreria());
            e.setAltura(vd.getAltura());
            e.setCantPiezas(vd.getCantPiezas());
        } else if (p instanceof TrajeCaballero tc) {
            e.setTipo(PrendaEntity.Tipo.TRAJE_CABALLERO);
            e.setTipoTraje(tc.getTipoTraje());
            e.setAderezo(tc.getAderezo());
        } else if (p instanceof Disfraz d) {
            e.setTipo(PrendaEntity.Tipo.DISFRAZ);
            e.setNombreDisfraz(d.getNombreDisfraz());
        } else {
            throw new IllegalArgumentException("Tipo de prenda no soportado: " + p.getClass());
        }

        return e;
    }

    // ===== Servicio =====
    public ServicioAlquiler toServicioDomain(ServicioAlquilerEntity e) {
        ServicioAlquiler s = new ServicioAlquiler();
        s.setNumero(e.getId());
        s.setCliente(toClienteDomain(e.getCliente()));
        s.setEmpleado(toEmpleadoDomain(e.getEmpleado()));
        s.setFechaSolicitud(e.getFechaSolicitud());
        s.setFechaAlquiler(e.getFechaAlquiler());
        s.setEstado(ServicioAlquiler.Estado.valueOf(e.getEstado().name()));
        s.setPrendas(e.getPrendas().stream().map(this::toPrendaDomain).collect(Collectors.toList()));
        return s;
    }

    // Lavandería
    public LavanderiaItem toLavanderiaDomain(LavanderiaItemEntity e) {
        LavanderiaItem i = new LavanderiaItem();
        i.setId(e.getId());
        i.setPrenda(toPrendaDomain(e.getPrenda()));
        i.setPrioridad(e.isPrioridad());
        i.setEstado(LavanderiaItem.Estado.valueOf(e.getEstado().name()));
        i.setFechaRegistro(e.getFechaRegistro());
        i.setFechaEnvio(e.getFechaEnvio());
        return i;
    }
}
