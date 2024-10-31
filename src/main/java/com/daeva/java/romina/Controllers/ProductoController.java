package com.daeva.java.romina.Controllers;

import com.daeva.java.romina.DTOs.ProductoDTO;
import com.daeva.java.romina.Mappers.ProductoMapper;
import com.daeva.java.romina.Services.ProductoService;
import com.daeva.java.romina.entities.Producto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;


    @GetMapping
    public List<ProductoDTO> getAllProductos() {
        return productoService.getAllProductos().stream()
                .map(ProductoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getProductoById(@PathVariable Long id) {
        return productoService.getProductoById(id)
                .map(ProductoMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> createProducto(@RequestBody ProductoDTO productoDTO) {
        Producto producto = ProductoMapper.toEntity(productoDTO);
        Producto savedProducto = productoService.createProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductoMapper.toDTO(savedProducto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> updateProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        Producto producto = ProductoMapper.toEntity(productoDTO);
        return productoService.updateProducto(id, producto)
                .map(updatedProducto -> ResponseEntity.ok(ProductoMapper.toDTO(updatedProducto)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        boolean isDeleted = productoService.deleteProducto(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build(); // 204 No Content si se eliminó correctamente
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found si no se encontró el producto
        }
    }
}

