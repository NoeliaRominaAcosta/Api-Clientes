package com.daeva.java.romina.Services;

import com.daeva.java.romina.Repository.VentaRepository;
import com.daeva.java.romina.entities.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;


    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }


    public Optional<Venta> getVentaById(Long id) {
        return ventaRepository.findById(id);
    }


    public Venta createVenta(Venta venta) {
        // Aquí podrías realizar validaciones adicionales, si es necesario
        return (Venta) ventaRepository.save(venta);
    }

    public Optional<Venta> updateVenta(Long id, Venta venta) {
        // Verifica si la venta existe
        if (!ventaRepository.existsById(id)) {
            return Optional.empty();
        }


        venta.setId(id);


        return Optional.of(ventaRepository.save(venta));
    }


    public boolean deleteVenta(Long id) {
        if (ventaRepository.existsById(id)) {
            ventaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}