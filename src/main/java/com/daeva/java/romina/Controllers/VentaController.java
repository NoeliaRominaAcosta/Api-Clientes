package com.daeva.java.romina.Controllers;

import com.daeva.java.romina.DTOs.VentaDTO;
import com.daeva.java.romina.Mappers.VentaMapper;
import com.daeva.java.romina.Services.ClienteService;
import com.daeva.java.romina.Services.VentaService;
import com.daeva.java.romina.entities.Cliente;
import com.daeva.java.romina.entities.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Venta> getAllVentas() {
        return ventaService.getAllVentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable Long id) {
        return ventaService.getVentaById(id)
                .map(venta -> ResponseEntity.ok(venta))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Venta> createVenta(@RequestBody Venta ventaDTO) {
        // Verifica que el cliente y su ID están presentes
        if (ventaDTO.getCliente() == null || ventaDTO.getCliente().getId() == null) {
            return ResponseEntity.badRequest().body(null); // Retorna un error si el cliente no es válido
        }

        // Verifica que el cliente existe en la base de datos
        Optional<Cliente> clienteOpt = clienteService.getClienteById(ventaDTO.getCliente().getId());
        if (!clienteOpt.isPresent()) {
            return ResponseEntity.badRequest().body(null); // Retorna un error si el cliente no existe
        }


        return ResponseEntity.status(HttpStatus.CREATED).body(ventaDTO);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Venta> updateVenta(@PathVariable Long id, @RequestBody Venta venta) {
        return ventaService.updateVenta(id, venta)
                .map(updatedVenta -> ResponseEntity.ok(updatedVenta))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Long id) {
        if (ventaService.deleteVenta(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

