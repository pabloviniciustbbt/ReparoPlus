package com.pabloleal.ReparoPlus.dto;

import com.pabloleal.ReparoPlus.models.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record ClienteUpdateRequestDTO(

        @NotNull
        Long id,
        String cpf,
        String nome,
        @Email
        String email,
        String telefone,
        EnderecoUpdateDTO endereco

) {
    public ClienteUpdateRequestDTO(Cliente cliente) {
        this(
                cliente.getId(),
                cliente.getCpf(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                new EnderecoUpdateDTO(cliente.getEndereco()));
    }
}
