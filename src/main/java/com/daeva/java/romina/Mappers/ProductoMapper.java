package com.daeva.java.romina.Mappers;

import com.daeva.java.romina.DTOs.ProductoDTO;
import com.daeva.java.romina.entities.Producto;

public class ProductoMapper {

    public static ProductoDTO toDTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        // Asigna otros campos si es necesario
        return dto;
    }

    public static Producto toEntity(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setId(productoDTO.getId());
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        // Asigna otros campos si es necesario
        return producto;
    }
}

