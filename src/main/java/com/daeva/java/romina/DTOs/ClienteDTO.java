package com.daeva.java.romina.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDTO {
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

}

