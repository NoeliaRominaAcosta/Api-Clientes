package com.daeva.java.romina.Controllers;

import com.daeva.java.romina.DTOs.ClienteDTO;
import com.daeva.java.romina.DTOs.VentaDTO;
import com.daeva.java.romina.Mappers.ClienteMapper;
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

    //TODO: al momento de probar por postman, esto está fallando porque no encuentra el cliente que le paso, pero puede ser que estoy enviando mal el body del request
    @PostMapping
    public ResponseEntity<Venta> createVenta(@RequestBody VentaDTO ventaDTO) {
        try {
            Venta nuevaVenta = ventaService.createVenta(ventaDTO);
            return new ResponseEntity<>(nuevaVenta, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // Cliente no encontrado
        } catch (Exception e) {
            // Manejo de otros errores que puedan ocurrir
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

