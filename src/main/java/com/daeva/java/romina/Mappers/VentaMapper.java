package com.daeva.java.romina.Mappers;

import com.daeva.java.romina.DTOs.VentaDTO;
import com.daeva.java.romina.entities.Cliente;
import com.daeva.java.romina.entities.Venta;

import java.util.stream.Collectors;

public class VentaMapper {
    public static VentaDTO toDTO(Venta venta) {
        VentaDTO dto = new VentaDTO();
        dto.setId(venta.getId());
        dto.setFecha(venta.getFecha());
        dto.setTotal(venta.getTotal());
        dto.setCantidad(venta.getCantidad());
        // Convertir Cliente a ClienteDTO
        dto.setCliente(ClienteMapper.toDTO(venta.getCliente())); // Asegúrate de tener este método

        // Asignar los IDs de DetalleVenta
        dto.setDetallesIds(
                venta.getDetalles().stream()
                        .map(detalle -> detalle.getId())
                        .collect(Collectors.toList())
        );

        return dto;
    }


    public static Venta toEntity(VentaDTO ventaDTO, Cliente cliente) {
        Venta venta = new Venta();
        venta.setId(ventaDTO.getId()); // Este ID puede ser nulo si estás creando una nueva venta
        venta.setFecha(ventaDTO.getFecha());
        venta.setTotal(ventaDTO.getTotal());
        venta.setCantidad(ventaDTO.getCantidad());
        // Asigna el cliente existente
        venta.setCliente(cliente);

        return venta;
    }

}
