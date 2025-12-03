package com.pabloleal.ReparoPlus.dto;

import com.pabloleal.ReparoPlus.models.Cliente;
import com.pabloleal.ReparoPlus.models.Endereco;

public record ClienteResponseDTO(

        Long id,
        String cpf,
        String nome,
        String email,
        String telefone,
        Endereco endereco

){
    public ClienteResponseDTO(Long id, String cpf, String nome, String email, String telefone, Endereco endereco) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public ClienteResponseDTO(Cliente cliente){
        this(
                cliente.getId(),
                cliente.getCpf(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getEndereco()
        );
    }

}
