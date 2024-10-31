package com.daeva.java.romina.Services;

import com.daeva.java.romina.DTOs.VentaDTO;
import com.daeva.java.romina.Repository.ClienteRepository;
import com.daeva.java.romina.Repository.DetalleVentaRepository;
import com.daeva.java.romina.Repository.VentaRepository;
import com.daeva.java.romina.entities.Cliente;
import com.daeva.java.romina.entities.DetalleVenta;
import com.daeva.java.romina.entities.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    DetalleVentaRepository detalleVentaRepository;


    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }


    public Optional<Venta> getVentaById(Long id) {
        return ventaRepository.findById(id);
    }


    public Venta createVenta(VentaDTO ventaDTO) {
        Venta venta = new Venta();
        venta.setFecha(ventaDTO.getFecha());
        venta.setTotal(ventaDTO.getTotal());

        // Asigna el cliente utilizando el objeto cliente en lugar de solo el ID
        Cliente cliente = clienteRepository.findById(ventaDTO.getCliente().getId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        venta.setCliente(cliente);

        // Asigna los detalles usando los IDs de DetalleVenta
        List<DetalleVenta> detalles = detalleVentaRepository.findAllById(ventaDTO.getDetallesIds());
        venta.setDetalles(detalles);

        return ventaRepository.save(venta);
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