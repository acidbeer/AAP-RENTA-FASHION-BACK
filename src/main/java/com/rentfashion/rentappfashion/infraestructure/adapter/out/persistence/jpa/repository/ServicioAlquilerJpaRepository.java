package com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.repository;

import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.entity.ServicioAlquilerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ServicioAlquilerJpaRepository extends JpaRepository<ServicioAlquilerEntity, Long> {

    List<ServicioAlquilerEntity> findByCliente_PersonaIdAndFechaAlquilerGreaterThanEqualOrderByFechaAlquilerAsc(String clienteId, LocalDate hoy);
    List<ServicioAlquilerEntity> findByFechaAlquiler(LocalDate fechaAlquiler);

    @Query("""
      select (count(s) > 0)
      from ServicioAlquilerEntity s
      join s.prendas p
      where s.fechaAlquiler = :fecha
        and p.ref = :ref
        and s.estado = com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.entity.ServicioAlquilerEntity.Estado.VIGENTE
   
    """)
    boolean existsPrendaAlquiladaEnFecha(String ref, LocalDate fecha);
}
