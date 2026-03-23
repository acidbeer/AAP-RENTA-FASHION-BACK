package com.rentfashion.rentappfashion.infraestructure.config;

import com.rentfashion.rentappfashion.application.port.out.ClienteRepositoryPort;
import com.rentfashion.rentappfashion.application.port.out.EmpleadoRepositoryPort;
import com.rentfashion.rentappfashion.application.port.out.LavanderiaRepositoryPort;
import com.rentfashion.rentappfashion.application.port.out.PrendaRepositoryPort;
import com.rentfashion.rentappfashion.application.port.out.ServicioAlquilerRepositoryPort;
import com.rentfashion.rentappfashion.application.service.command.CommandInvoker;
import com.rentfashion.rentappfashion.application.service.observer.ActualizarEstadoPrendaObserver;
import com.rentfashion.rentappfashion.application.service.observer.AlquilerEventPublisher;
import com.rentfashion.rentappfashion.application.service.ConsultasServicioService;
import com.rentfashion.rentappfashion.application.service.LavanderiaService;
import com.rentfashion.rentappfashion.application.service.strategy.ProcesamientoAlquilerBasicoStrategy;
import com.rentfashion.rentappfashion.application.service.RegistrarClienteService;
import com.rentfashion.rentappfashion.application.service.RegistrarEmpleadoService;
import com.rentfashion.rentappfashion.application.service.RegistrarPrendaService;
import com.rentfashion.rentappfashion.application.service.RegistrarServicioAlquilerService;
import com.rentfashion.rentappfashion.application.service.observer.AlquilerObserver;
import com.rentfashion.rentappfashion.application.service.strategy.ProcesamientoAlquilerStrategy;
import com.rentfashion.rentappfashion.domain.factory.PrendaFactory;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.ClienteRepositoryAdapter;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.EmpleadoRepositoryAdapter;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.LavanderiaRepositoryAdapter;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.PrendaRepositoryAdapter;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.ServicioAlquilerRepositoryAdapter;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.mapper.EntityMapper;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.repository.ClienteJpaRepository;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.repository.EmpleadoJpaRepository;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.repository.LavanderiaJpaRepository;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.repository.PersonaJpaRepository;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.repository.PrendaJpaRepository;
import com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.repository.ServicioAlquilerJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BeanConfig {

    // Mapper + Factory
    @Bean
    public EntityMapper entityMapper() { return new EntityMapper(); }
    @Bean public PrendaFactory prendaFactory() { return new PrendaFactory(); }

    // Adapters (Ports OUT)
    @Bean
    public ClienteRepositoryPort clienteRepositoryPort(PersonaJpaRepository personaRepo, ClienteJpaRepository clienteRepo, EntityMapper mapper) {
        return new ClienteRepositoryAdapter(personaRepo, clienteRepo, mapper);
    }

    @Bean
    public EmpleadoRepositoryPort empleadoRepositoryPort(PersonaJpaRepository personaRepo, EmpleadoJpaRepository empleadoRepo, EntityMapper mapper) {
        return new EmpleadoRepositoryAdapter(personaRepo, empleadoRepo, mapper);
    }

    @Bean
    public PrendaRepositoryPort prendaRepositoryPort(PrendaJpaRepository prendaRepo, EntityMapper mapper) {
        return new PrendaRepositoryAdapter(prendaRepo, mapper);
    }

    //Nuevos Bean
    @Bean
    public ProcesamientoAlquilerStrategy procesamientoAlquilerStrategy() {
        return new ProcesamientoAlquilerBasicoStrategy();
    }

    @Bean
    public AlquilerObserver actualizarEstadoPrendaObserver(PrendaRepositoryPort prendaRepo) {
        return new ActualizarEstadoPrendaObserver(prendaRepo);
    }

    @Bean
    public AlquilerEventPublisher alquilerEventPublisher(List<AlquilerObserver> observers) {
        return new AlquilerEventPublisher(observers);
    }

    @Bean
    public CommandInvoker commandInvoker() {
        return new CommandInvoker();
    }

    @Bean
    public ServicioAlquilerRepositoryPort servicioAlquilerRepositoryPort(
            ServicioAlquilerJpaRepository repo,
            ClienteJpaRepository clienteRepo,
            EmpleadoJpaRepository empleadoRepo,
            PrendaJpaRepository prendaRepo,
            EntityMapper mapper
    ) {
        return new ServicioAlquilerRepositoryAdapter(repo, clienteRepo, empleadoRepo, prendaRepo, mapper);
    }

    @Bean
    public LavanderiaRepositoryPort lavanderiaRepositoryPort(LavanderiaJpaRepository repo, PrendaJpaRepository prendaRepo, EntityMapper mapper) {
        return new LavanderiaRepositoryAdapter(repo, prendaRepo, mapper);
    }

    // UseCases (Services)
    @Bean
    public RegistrarPrendaService registrarPrendaService(PrendaRepositoryPort prendaRepo, PrendaFactory factory) {
        return new RegistrarPrendaService(prendaRepo, factory);
    }

    @Bean
    public RegistrarClienteService registrarClienteService(ClienteRepositoryPort repo) {
        return new RegistrarClienteService(repo);
    }

    @Bean
    public RegistrarEmpleadoService registrarEmpleadoService(EmpleadoRepositoryPort repo) {
        return new RegistrarEmpleadoService(repo);
    }

    @Bean
    public RegistrarServicioAlquilerService registrarServicioAlquilerService(
            ClienteRepositoryPort clienteRepo,
            EmpleadoRepositoryPort empleadoRepo,
            PrendaRepositoryPort prendaRepo,
            ServicioAlquilerRepositoryPort servicioRepo,
            ProcesamientoAlquilerStrategy procesamientoAlquilerStrategy,
            AlquilerEventPublisher alquilerEventPublisher
    ) {
        return new RegistrarServicioAlquilerService(
                clienteRepo,
                empleadoRepo,
                prendaRepo,
                servicioRepo,
                procesamientoAlquilerStrategy,
                alquilerEventPublisher
        );
    }

    @Bean
    public ConsultasServicioService consultasServicioService(ServicioAlquilerRepositoryPort servicioRepo, PrendaRepositoryPort prendaRepo) {
        return new ConsultasServicioService(servicioRepo, prendaRepo);
    }

    @Bean
    public LavanderiaService lavanderiaService(LavanderiaRepositoryPort lavRepo, PrendaRepositoryPort prendaRepo) {
        return new LavanderiaService(lavRepo, prendaRepo);
    }
}
