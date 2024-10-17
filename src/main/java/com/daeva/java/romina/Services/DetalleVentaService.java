package com.daeva.java.romina.Services;

import com.daeva.java.romina.Repository.DetalleVentaRepository;
import com.daeva.java.romina.entities.DetalleVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;


    public List<DetalleVenta> getAllDetallesVenta() {
        return detalleVentaRepository.findAll();
    }

    public Optional<DetalleVenta> getDetalleVentaById(Long id) {
        return detalleVentaRepository.findById(id);
    }

    public DetalleVenta createDetalleVenta(DetalleVenta detalleVenta) {
        // Aquí podrías realizar validaciones adicionales, si es necesario
        return detalleVentaRepository.save(detalleVenta);
    }


    public Optional<DetalleVenta> updateDetalleVenta(Long id, DetalleVenta detalleVenta) {
        // Verifica si el detalle de venta existe
        if (!detalleVentaRepository.existsById(id)) {
            return Optional.empty();
        }

        // Establecer el ID del detalle de venta a actualizar
        detalleVenta.setId(id);

        // Guardar y devolver el detalle de venta actualizado
        return Optional.of(detalleVentaRepository.save(detalleVenta));
    }


    public boolean deleteDetalleVenta(Long id) {
        if (detalleVentaRepository.existsById(id)) {
            detalleVentaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
