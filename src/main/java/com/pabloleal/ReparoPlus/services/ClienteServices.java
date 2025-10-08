package com.pabloleal.ReparoPlus.services;

import com.pabloleal.ReparoPlus.dto.ClienteCreateRequestDTO;
import com.pabloleal.ReparoPlus.dto.ClienteUpdateRequestDTO;
import com.pabloleal.ReparoPlus.models.Cliente;
import com.pabloleal.ReparoPlus.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServices {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteCreateRequestDTO cadastrarCliente(ClienteCreateRequestDTO dados){

        Cliente cliente = new Cliente(dados);
        clienteRepository.save(cliente);
        return dados;

    }

    public ClienteUpdateRequestDTO atualizarCliente(ClienteUpdateRequestDTO dados) {

        Cliente cliente = clienteRepository.getReferenceByCpf(dados.cpf());
        cliente.atualizarCliente(dados);
        return new ClienteUpdateRequestDTO(cliente);
    }

    public void deletarCliente(Long id) {

        Cliente cliente = clienteRepository.getReferenceById(id);
        cliente.deletarPessoa();

    }
}
