package com.daeva.java.romina.Mappers;

import com.daeva.java.romina.DTOs.ClienteDTO;
import com.daeva.java.romina.entities.Cliente;

public class ClienteMapper {

    public static ClienteDTO toDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNombre(cliente.getNombre());
        dto.setEmail(cliente.getEmail());
        // asignar otros campos necesarios
        return dto;
    }

    public static Cliente toEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setEmail(clienteDTO.getEmail());
        // asignar otros campos necesarios
        return cliente;
    }
}
