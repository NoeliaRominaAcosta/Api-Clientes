package com.daeva.java.romina.Repository;

import com.daeva.java.romina.entities.DetalleVenta;
import com.daeva.java.romina.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
}
