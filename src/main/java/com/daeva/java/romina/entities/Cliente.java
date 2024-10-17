package com.daeva.java.romina.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="Cliente")
@Getter
@Setter
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String dni;
    private String telefono;
    private String direccion;
    private String ciudad;
    private String codigoPostal;
    private LocalDate fechaNacimiento;
    private String genero;
    private LocalDate fechaRegistro;
    @OneToMany(mappedBy = "cliente")
    private List<Venta> ventas;

}
