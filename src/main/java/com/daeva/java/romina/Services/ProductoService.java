package com.daeva.java.romina.Services;

import com.daeva.java.romina.Repository.ProductoRepository;
import com.daeva.java.romina.entities.Producto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;


    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }


    public Optional<Producto> getProductoById(Long id) {
        return productoRepository.findById(id);
    }

    /**
     * Crear un nuevo producto.
     *
     * @param producto el producto a crear
     * @return el producto creado
     */
    public Producto createProducto(Producto producto) {
        // Aquí podrías realizar validaciones adicionales, si es necesario
        return (Producto) productoRepository.save(producto);
    }


    public Optional<Producto> updateProducto(Long id, Producto producto) {
        // Verifica si el producto existe
        if (!productoRepository.existsById(id)) {
            return Optional.empty();
        }
        // Establecer el ID del producto a actualizar
        producto.setId(id);
        // Guardar y devolver el producto actualizado
        return Optional.of(productoRepository.save(producto));  // save ya devuelve el producto actualizado
    }


    public boolean deleteProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

