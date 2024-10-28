package com.daeva.java.romina.Controllers;

import com.daeva.java.romina.DTOs.ClienteDTO;
import com.daeva.java.romina.Mappers.ClienteMapper;
import com.daeva.java.romina.Services.ClienteService;
import com.daeva.java.romina.entities.Cliente;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {


    @Autowired
    private ClienteService clienteService;


    @GetMapping
    public List<ClienteDTO> getAllClientes() {
        return clienteService.getAllClientes().stream()
                .map(ClienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) {
        return clienteService.getClienteById(id)
                .map(ClienteMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = ClienteMapper.toEntity(clienteDTO);
        Cliente savedCliente = clienteService.createCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteMapper.toDTO(savedCliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = ClienteMapper.toEntity(clienteDTO);
        return clienteService.updateCliente(id, cliente)
                .map(updatedCliente -> ResponseEntity.ok(ClienteMapper.toDTO(updatedCliente)))
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        boolean isDeleted = clienteService.deleteCliente(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build(); // Responde con 204 No Content si se eliminó correctamente
        } else {
            return ResponseEntity.notFound().build(); // Responde con 404 Not Found si el cliente no fue encontrado
        }
    }

}

