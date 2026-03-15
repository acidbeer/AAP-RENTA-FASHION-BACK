package com.rentfashion.rentappfashion.infraestructure.adapter.in.web;

import com.rentfashion.rentappfashion.application.port.in.EnviarLavanderiaUseCase;
import com.rentfashion.rentappfashion.application.port.in.RegistrarPrendaLavanderiaUseCase;
import com.rentfashion.rentappfashion.application.port.in.VerListaLavanderiaUseCase;
import com.rentfashion.rentappfashion.domain.model.LavanderiaItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lavanderia")
public class LavanderiaController {

    private final RegistrarPrendaLavanderiaUseCase registrar;
    private final VerListaLavanderiaUseCase ver;
    private final EnviarLavanderiaUseCase enviar;

    public LavanderiaController(RegistrarPrendaLavanderiaUseCase registrar, VerListaLavanderiaUseCase ver, EnviarLavanderiaUseCase enviar) {
        this.registrar = registrar;
        this.ver = ver;
        this.enviar = enviar;
    }

    public record RegistrarLavanderiaRequest(String refPrenda, boolean prioridad) {}

    @PostMapping("/registrar")
    public ResponseEntity<Void> registrar(@RequestBody RegistrarLavanderiaRequest req) {
        registrar.registrar(req.refPrenda(), req.prioridad());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/pendientes")
    public ResponseEntity<List<LavanderiaItem>> pendientes() {
        return ResponseEntity.ok(ver.verPendientes());
    }

    @PostMapping("/enviar")
    public ResponseEntity<List<LavanderiaItem>> enviar(@RequestParam int cantidad) {
        return ResponseEntity.ok(enviar.enviar(cantidad));
    }
}
