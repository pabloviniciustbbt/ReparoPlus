package com.pabloleal.ReparoPlus.services;

import com.pabloleal.ReparoPlus.dto.cliente.ClienteCreateRequestDTO;
import com.pabloleal.ReparoPlus.dto.cliente.ClienteResponseDTO;
import com.pabloleal.ReparoPlus.dto.cliente.ClienteUpdateRequestDTO;
import com.pabloleal.ReparoPlus.exceptions.EntidadeAtivaInativaException;
import com.pabloleal.ReparoPlus.exceptions.EntidadeCadastradaException;
import com.pabloleal.ReparoPlus.models.Cliente;
import com.pabloleal.ReparoPlus.models.Pessoa;
import com.pabloleal.ReparoPlus.repositories.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ClienteServices {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente cadastrarCliente(ClienteCreateRequestDTO dados) {

        String cpfFormatado = Pessoa.formatarCpf(dados.cpf());

        if (clienteRepository.existsByCpf(cpfFormatado)) {
            Cliente clienteCadastrado = clienteRepository.getReferenceByCpf(cpfFormatado);
            throw new EntidadeCadastradaException("Cliente com CPF " + cpfFormatado + " já cadastrado com ID " + clienteCadastrado.getId());
        }

        Cliente cliente = new Cliente(dados);
        clienteRepository.save(cliente);
        return cliente;

    }

    public Cliente atualizarCliente(ClienteUpdateRequestDTO dados) {
        Cliente cliente = clienteRepository.findById(dados.id()).
                orElseThrow(() -> new EntityNotFoundException("Cliente com ID " + dados.id() + " não encontrado"));


        if (dados.cpf() != null) {
            String cpfFormatado = Pessoa.formatarCpf(dados.cpf());

            if (clienteRepository.existsByCpf(cpfFormatado)) {
                Cliente clienteCadastrado = clienteRepository.getReferenceByCpf(cpfFormatado);
                throw new EntidadeCadastradaException("Cliente com CPF " + cpfFormatado + " já cadastrado com ID " + clienteCadastrado.getId());
            }
        }

        cliente.atualizarCliente(dados);
        return cliente;
    }

    public void deletarCliente(Long id) {

        Cliente cliente = clienteRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Cliente com ID " + id + " não encontrado"));

        if (!cliente.getAtivo()) {
            throw new EntidadeAtivaInativaException("Cliente com ID " + id + " já está inativo");
        }

        cliente.deletarPessoa();

    }

    public Cliente ativarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Cliente com ID " + id + " não encontrado"));

        if (cliente.getAtivo()) {
            throw new EntidadeAtivaInativaException("Cliente com ID " + id + " já está ativo");
        }

        cliente.ativarPessoa();
        return cliente;
    }

    public Cliente buscarClienteID(Long id) {
        Cliente cliente = clienteRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Cliente com ID " + id + " não encontrado"));

        return cliente;
    }

    public Cliente buscarClienteCPF(String cpf) {
        String cpfFormatado = Pessoa.formatarCpf(cpf);
        Cliente cliente = clienteRepository.findByCpf(cpfFormatado).
                orElseThrow(() -> new EntityNotFoundException("Cliente com CPF " + cpfFormatado + " não encontrado"));
        return cliente;
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
