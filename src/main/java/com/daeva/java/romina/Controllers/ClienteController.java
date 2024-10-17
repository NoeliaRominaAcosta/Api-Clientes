package com.daeva.java.romina.Controllers;

import com.daeva.java.romina.Services.ClienteService;
import com.daeva.java.romina.entities.Cliente;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {


    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Obtener todos los clientes")
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cliente por ID")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        return clienteService.getClienteById(id)
                .map(cliente -> ResponseEntity.ok(cliente))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo cliente")
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente savedCliente = clienteService.createCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCliente);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un cliente existente")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteService.updateCliente(id,cliente)
                .map(updatedCliente -> ResponseEntity.ok(updatedCliente))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un cliente por ID")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        if (clienteService.deleteCliente(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

