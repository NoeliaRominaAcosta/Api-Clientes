package com.daeva.java.romina.Mappers;

import com.daeva.java.romina.DTOs.ClienteDTO;
import com.daeva.java.romina.entities.Cliente;

public class ClienteMapper {

    public static ClienteDTO toDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNombre(cliente.getNombre());
        dto.setApellido(cliente.getApellido());
        dto.setEmail(cliente.getEmail());
        dto.setDireccion(cliente.getDni());
        dto.setCiudad(cliente.getCiudad());
        dto.setGenero(cliente.getCiudad());
        dto.setFechaNacimiento(cliente.getFechaNacimiento());
        dto.setFechaRegistro(cliente.getFechaRegistro());
        dto.setCodigoPostal(cliente.getCodigoPostal());
        dto.setDni(cliente.getDni());
        dto.setTelefono(cliente.getTelefono());
        return dto;
    }

    public static Cliente toEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setDireccion(clienteDTO.getDireccion());
        cliente.setCiudad(clienteDTO.getCiudad());
        cliente.setGenero(clienteDTO.getGenero());
        cliente.setFechaNacimiento(clienteDTO.getFechaNacimiento());
        cliente.setFechaRegistro(clienteDTO.getFechaRegistro());
        cliente.setCodigoPostal(clienteDTO.getCodigoPostal());
        cliente.setDni(clienteDTO.getDni());
        cliente.setTelefono(clienteDTO.getTelefono());
        return cliente;
    }
}
