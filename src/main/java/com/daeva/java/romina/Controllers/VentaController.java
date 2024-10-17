package com.daeva.java.romina.Controllers;

import com.daeva.java.romina.Services.VentaService;
import com.daeva.java.romina.entities.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

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
    public ResponseEntity<Venta> createVenta(@RequestBody Venta venta) {
        // Verifica que el cliente y su ID están presentes
        if (venta.getCliente() == null || venta.getCliente().getId() == null) {
            return ResponseEntity.badRequest().body(null); // Retorna un error si el cliente no es válido
        }

        // Llama al servicio para guardar la nueva venta
        Venta nuevaVenta = ventaService.createVenta(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
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

