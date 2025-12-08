package com.pabloleal.ReparoPlus.services;

import com.pabloleal.ReparoPlus.dto.ClienteCreateRequestDTO;
import com.pabloleal.ReparoPlus.dto.ClienteResponseDTO;
import com.pabloleal.ReparoPlus.dto.ClienteUpdateRequestDTO;
import com.pabloleal.ReparoPlus.models.Cliente;
import com.pabloleal.ReparoPlus.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public ClienteResponseDTO buscarClienteID(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isPresent()){
            Cliente c = cliente.get();
            return new ClienteResponseDTO(c.getId(), c.getCpf(), c.getNome(),c.getTelefone(), c.getEmail(), c.getEndereco());
        }

        return null;
    }

    public ClienteResponseDTO buscarClienteCPF(String cpf) {
        Optional<Cliente> cliente = clienteRepository.findByCpf(cpf);

        if (cliente.isPresent()){
            Cliente c = cliente.get();
            return new ClienteResponseDTO(c.getId(), c.getCpf(), c.getNome(),c.getTelefone(), c.getEmail(), c.getEndereco());
        }

        return null;
    }

    public Page<ClienteResponseDTO> listarClientes(Pageable pageable) {
        return clienteRepository.findAll(pageable)
                .map(ClienteResponseDTO::new);
    }

    public Page<ClienteResponseDTO> listarClientesAtivos(Pageable pageable) {
        return clienteRepository.findAllByAtivoTrue(pageable)
                .map(ClienteResponseDTO::new);
    }

    public Page<ClienteResponseDTO> listarClientesInativos(Pageable pageable) {
        return clienteRepository.findAllByAtivoFalse(pageable)
                .map(ClienteResponseDTO::new);
    }
}
