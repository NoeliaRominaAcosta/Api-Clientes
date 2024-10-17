package com.daeva.java.romina.Controllers;

import com.daeva.java.romina.Services.DetalleVentaService;
import com.daeva.java.romina.entities.DetalleVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-venta")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @GetMapping
    public List<DetalleVenta> getAllDetallesVenta() {
        return detalleVentaService.getAllDetallesVenta();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVenta> getDetalleById(@PathVariable Long id) {
        return detalleVentaService.getDetalleVentaById(id)
                .map(detalle -> ResponseEntity.ok(detalle))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DetalleVenta> createDetalle(@RequestBody DetalleVenta detalleVenta) {
        DetalleVenta savedDetalle = detalleVentaService.createDetalleVenta(detalleVenta);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDetalle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleVenta> updateDetalle(@PathVariable Long id, @RequestBody DetalleVenta detalleVenta) {
        return detalleVentaService.updateDetalleVenta(id, detalleVenta)
                .map(updatedDetalle -> ResponseEntity.ok(updatedDetalle))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalle(@PathVariable Long id) {
        if (detalleVentaService.deleteDetalleVenta(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

