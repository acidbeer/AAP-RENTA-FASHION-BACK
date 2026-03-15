package com.rentfashion.rentappfashion.infraestructure.adapter.in.web;

import com.rentfashion.rentappfashion.application.port.in.ConsultarPrendasPorTallaUseCase;
import com.rentfashion.rentappfashion.application.port.in.ConsultarServicioUseCase;
import com.rentfashion.rentappfashion.application.port.in.ConsultarServiciosPorClienteUseCase;
import com.rentfashion.rentappfashion.application.port.in.ConsultarServiciosPorFechaUseCase;
import com.rentfashion.rentappfashion.application.port.in.RegistrarServicioAlquilerUseCase;
import com.rentfashion.rentappfashion.domain.model.ServicioAlquiler;
import com.rentfashion.rentappfashion.domain.model.prenda.Prenda;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/servicios")
public class ServicioAlquilerController {

    private final RegistrarServicioAlquilerUseCase registrarServicio;
    private final ConsultarServicioUseCase consultarServicio;
    private final ConsultarServiciosPorClienteUseCase consultarPorCliente;
    private final ConsultarServiciosPorFechaUseCase consultarPorFecha;

    private final ConsultarPrendasPorTallaUseCase consultarPorTalla;

    public ServicioAlquilerController(
            RegistrarServicioAlquilerUseCase registrarServicio,
            ConsultarServicioUseCase consultarServicio,
            ConsultarServiciosPorClienteUseCase consultarPorCliente,
            ConsultarServiciosPorFechaUseCase consultarPorFecha,
            ConsultarPrendasPorTallaUseCase consultarPorTalla
    ) {
        this.registrarServicio = registrarServicio;
        this.consultarServicio = consultarServicio;
        this.consultarPorCliente = consultarPorCliente;
        this.consultarPorFecha = consultarPorFecha;
        this.consultarPorTalla = consultarPorTalla;
    }

    public record RegistrarServicioRequest(String clienteId, String empleadoId, List<String> refsPrenda, LocalDate fechaAlquiler) {}

    @PostMapping
    public ResponseEntity<Long> registrar(@RequestBody RegistrarServicioRequest req) {
        Long numero = registrarServicio.registrarServicio(req.clienteId(), req.empleadoId(), req.refsPrenda(), req.fechaAlquiler());
        return ResponseEntity.ok(numero);
    }

    @GetMapping("/{numero}")
    public ResponseEntity<ServicioAlquiler> consultar(@PathVariable Long numero) {
        return ResponseEntity.ok(consultarServicio.consultarPorNumero(numero));
    }

    @GetMapping("/vigentes/cliente/{clienteId}")
    public ResponseEntity<List<ServicioAlquiler>> vigentesCliente(@PathVariable String clienteId) {
        return ResponseEntity.ok(consultarPorCliente.consultarVigentes(clienteId));
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<ServicioAlquiler>> porFecha(@PathVariable LocalDate fecha) {
        return ResponseEntity.ok(consultarPorFecha.consultarPorFecha(fecha));
    }

    @GetMapping("/talla/{talla}")
    public ResponseEntity<Map<String, List<Prenda>>> porTalla(@PathVariable String talla){
        return ResponseEntity.ok(consultarPorTalla.consultarPorTallaSeparadasPorTipo(talla));
    }

}
