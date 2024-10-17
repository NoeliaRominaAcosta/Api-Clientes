package com.daeva.java.romina.Repository;

import com.daeva.java.romina.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
