package com.rentfashion.rentappfashion.application.service;

import com.rentfashion.rentappfashion.application.port.in.RegistrarServicioAlquilerUseCase;
import com.rentfashion.rentappfashion.application.port.out.ClienteRepositoryPort;
import com.rentfashion.rentappfashion.application.port.out.EmpleadoRepositoryPort;
import com.rentfashion.rentappfashion.application.port.out.PrendaRepositoryPort;
import com.rentfashion.rentappfashion.application.service.observer.AlquilerEvent;
import com.rentfashion.rentappfashion.application.service.observer.AlquilerEventPublisher;
import com.rentfashion.rentappfashion.application.service.strategy.ProcesamientoAlquilerStrategy;
import com.rentfashion.rentappfashion.application.port.out.ServicioAlquilerRepositoryPort;
import com.rentfashion.rentappfashion.application.service.policy.DisponibilidadPrendaPolicy;
import com.rentfashion.rentappfashion.domain.exception.BusinessRuleException;
import com.rentfashion.rentappfashion.domain.exception.NotFoundException;
import com.rentfashion.rentappfashion.domain.exception.ValidationException;
import com.rentfashion.rentappfashion.domain.model.Cliente;
import com.rentfashion.rentappfashion.domain.model.Empleado;
import com.rentfashion.rentappfashion.domain.model.ServicioAlquiler;
import com.rentfashion.rentappfashion.domain.model.prenda.Prenda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RegistrarServicioAlquilerService  implements RegistrarServicioAlquilerUseCase {

    private final ClienteRepositoryPort clienteRepo;
    private final EmpleadoRepositoryPort empleadoRepo;
    private final PrendaRepositoryPort prendaRepo;
    private final ServicioAlquilerRepositoryPort servicioRepo;
    private final DisponibilidadPrendaPolicy disponibilidadPolicy;

    // NUEVO: Strategy
    private final ProcesamientoAlquilerStrategy procesamientoAlquilerStrategy;

    // NUEVO: Observer publisher
    private final AlquilerEventPublisher alquilerEventPublisher;

    public RegistrarServicioAlquilerService(
            ClienteRepositoryPort clienteRepo,
            EmpleadoRepositoryPort empleadoRepo,
            PrendaRepositoryPort prendaRepo,
            ServicioAlquilerRepositoryPort servicioRepo,
            ProcesamientoAlquilerStrategy procesamientoAlquilerStrategy,
            AlquilerEventPublisher alquilerEventPublisher
    ) {
        this.clienteRepo = clienteRepo;
        this.empleadoRepo = empleadoRepo;
        this.prendaRepo = prendaRepo;
        this.servicioRepo = servicioRepo;
        this.disponibilidadPolicy = new DisponibilidadPrendaPolicy(servicioRepo);
        this.procesamientoAlquilerStrategy = procesamientoAlquilerStrategy;
        this.alquilerEventPublisher = alquilerEventPublisher;
    }

    @Override
    public Long registrarServicio(String clienteId, String empleadoId, List<String> refsPrenda, LocalDate fechaAlquiler) {

        //  1. Validaciones
        validarEntrada(clienteId, empleadoId, refsPrenda, fechaAlquiler);

        //  2. Obtener entidades
        Cliente cliente = clienteRepo.findById(clienteId)
                .orElseThrow(() -> new NotFoundException("Cliente no existe: " + clienteId));

        Empleado empleado = empleadoRepo.findById(empleadoId)
                .orElseThrow(() -> new NotFoundException("Empleado no existe: " + empleadoId));

        //  3. Validar y obtener prendas
        List<Prenda> prendas = obtenerYValidarPrendas(refsPrenda, fechaAlquiler);

        //  4. Construir servicio
        ServicioAlquiler servicio = new ServicioAlquiler();
        servicio.setCliente(cliente);
        servicio.setEmpleado(empleado);
        servicio.setFechaSolicitud(LocalDateTime.now());
        servicio.setFechaAlquiler(fechaAlquiler);
        servicio.setEstado(ServicioAlquiler.Estado.VIGENTE);
        servicio.setPrendas(prendas);

        //  5. Strategy
        procesamientoAlquilerStrategy.procesar(servicio);

        // 6. Persistir
        ServicioAlquiler guardado = servicioRepo.save(servicio);

        // 7. Observer
        alquilerEventPublisher.notifyObservers(
                new AlquilerEvent(
                        guardado.getNumero(),
                        cliente.getId(),
                        empleado.getId(),
                        prendas
                )
        );

        return guardado.getNumero();
    }

    private void validarEntrada(String clienteId, String empleadoId, List<String> refsPrenda, LocalDate fechaAlquiler) {
        if (clienteId == null || clienteId.isBlank()) {
            throw new ValidationException("clienteId es obligatorio");
        }
        if (empleadoId == null || empleadoId.isBlank()) {
            throw new ValidationException("empleadoId es obligatorio");
        }
        if (fechaAlquiler == null) {
            throw new ValidationException("fechaAlquiler es obligatoria");
        }
        if (refsPrenda == null || refsPrenda.isEmpty()) {
            throw new ValidationException("Debe incluir al menos 1 prenda");
        }
    }

    private List<Prenda> obtenerYValidarPrendas(List<String> refsPrenda, LocalDate fechaAlquiler) {
        List<Prenda> prendas = new ArrayList<>();

        for (String ref : refsPrenda) {
            if (ref == null || ref.isBlank()) {
                throw new ValidationException("Referencia de prenda inválida");
            }

            String refLimpia = ref.trim();

            Prenda prenda = prendaRepo.findByRef(refLimpia)
                    .orElseThrow(() -> new NotFoundException("Prenda no existe: " + refLimpia));

            if (prenda.getEstado() == Prenda.Estado.EN_LAVANDERIA) {
                throw new BusinessRuleException("La prenda " + refLimpia + " está en lavandería");
            }

            disponibilidadPolicy.asegurarDisponible(refLimpia, fechaAlquiler);
            prendas.add(prenda);
        }

        return prendas;
    }
}
