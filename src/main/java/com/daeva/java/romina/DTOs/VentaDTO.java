package com.daeva.java.romina.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class VentaDTO {
    private Long id;
    private LocalDate fecha;
    private Double total;
    private ClienteDTO cliente; // En lugar de clienteId
    private List<Long> detallesIds;
}
