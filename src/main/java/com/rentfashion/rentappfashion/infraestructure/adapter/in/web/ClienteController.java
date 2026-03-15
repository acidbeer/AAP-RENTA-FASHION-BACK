package com.rentfashion.rentappfashion.infraestructure.adapter.in.web;


import com.rentfashion.rentappfashion.application.port.in.RegistrarClienteUseCase;
import com.rentfashion.rentappfashion.domain.model.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final RegistrarClienteUseCase registrarCliente;

    public ClienteController(RegistrarClienteUseCase registrarCliente) {
        this.registrarCliente = registrarCliente;
    }

    @PostMapping
    public ResponseEntity<Cliente> registrar(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(registrarCliente.registrar(cliente));
    }
}
