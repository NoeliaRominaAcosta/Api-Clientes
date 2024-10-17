package com.daeva.java.romina.Services;

import com.daeva.java.romina.Repository.ClienteRepository;
import com.daeva.java.romina.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente createCliente(Cliente cliente) {
        return (Cliente) clienteRepository.save(cliente);
    }

    public Optional<Cliente> updateCliente(Long id, Cliente cliente) {
        if (!clienteRepository.existsById(id)) {
            return Optional.empty();
        }
        cliente.setId(id); // Asegurarse de que el ID esté configurado
        return Optional.of(clienteRepository.save(cliente));
    }

    public boolean deleteCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
