package com.daeva.java.romina.Services;

import com.daeva.java.romina.Controllers.DetalleVentaController;
import com.daeva.java.romina.DTOs.VentaDTO;
import com.daeva.java.romina.Repository.ClienteRepository;
import com.daeva.java.romina.Repository.DetalleVentaRepository;
import com.daeva.java.romina.Repository.ProductoRepository;
import com.daeva.java.romina.Repository.VentaRepository;
import com.daeva.java.romina.entities.Cliente;
import com.daeva.java.romina.entities.DetalleVenta;
import com.daeva.java.romina.entities.Producto;
import com.daeva.java.romina.entities.Venta;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private DetalleVentaService detalleVentaService;

    @Autowired
    private DetalleVentaController detalleVentaController;

    @Autowired
    private ProductoRepository productoRepository;

    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }


    public Optional<Venta> getVentaById(Long id) {
        return ventaRepository.findById(id);
    }



    @CircuitBreaker(name = "ventaService", fallbackMethod = "fallbackCreateVenta")
    public Venta createVenta(VentaDTO ventaDTO) {
        System.out.println("Creando venta para el cliente con ID: " + ventaDTO.getCliente().getId());

        Venta venta = new Venta();
        venta.setFecha(ventaDTO.getFecha());
        venta.setTotal(ventaDTO.getTotal());

        // Asigna el cliente utilizando el objeto cliente en lugar de solo el ID
        Cliente cliente = clienteRepository.findById(ventaDTO.getCliente().getId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        venta.setCliente(cliente);
        System.out.println("Cliente encontrado: " +cliente.getId() + " para el producto id " + ventaDTO.getDetallesIds());
        // Asigna los detalles usando los IDs de DetalleVenta

        List<DetalleVenta> detalles = detalleVentaRepository.findAllById(ventaDTO.getDetallesIds());
        venta.setDetalles(detalles);
        venta.setCantidad(ventaDTO.getDetallesIds().size());

        Venta ventaGuardada = ventaRepository.save(venta);

        Iterator<Long> iterator = ventaDTO.getDetallesIds().iterator();

        while (iterator.hasNext()) {
            DetalleVenta detVenta = new DetalleVenta();

            detVenta.setVenta(ventaGuardada);
            detVenta.setPrecioUnitario(ventaDTO.getTotal());

            Long productoId = iterator.next();

            Producto producto = productoRepository.findById(productoId)
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado para el ID: " + productoId));

            detVenta.setProducto(producto);
            detVenta.setCantidad(ventaDTO.getCantidad());
            System.out.println("Creando detalle venta");

            detalleVentaService.createDetalleVenta(detVenta);
        }

        return ventaGuardada;
    }

    // Método de fallback para manejar fallas
    public Venta fallbackCreateVenta(VentaDTO ventaDTO, Throwable throwable) {
        throw new RestClientException("Error al crear la venta: " + throwable.getMessage(), throwable);
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