package com.rentfashion.rentappfashion.infraestructure.adapter.in.web;

import com.rentfashion.rentappfashion.application.port.in.RegistrarPrendaUseCase;
import com.rentfashion.rentappfashion.domain.model.prenda.Prenda;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/prendas")
public class PrendaController {

    private final RegistrarPrendaUseCase registrarPrenda;

    public PrendaController(RegistrarPrendaUseCase registrarPrenda) {
        this.registrarPrenda = registrarPrenda;
    }

    @PostMapping
    public ResponseEntity<Prenda> registrar(@RequestParam String tipo, @RequestBody Map<String, Object> datos) {
        return ResponseEntity.ok(registrarPrenda.registrar(tipo, datos));
    }

}
