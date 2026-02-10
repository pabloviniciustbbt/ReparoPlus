package com.pabloleal.ReparoPlus.dto.cliente;

import com.pabloleal.ReparoPlus.models.Cliente;
import com.pabloleal.ReparoPlus.models.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteCreateRequestDTO(

        @NotBlank
        String cpf,
        @NotBlank
        String nome,
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotNull
        @Valid
        Endereco endereco

) {
        public ClienteCreateRequestDTO(Cliente cliente){
                this(cliente.getCpf(), cliente.getNome(), cliente.getEmail(), cliente.getTelefone(), cliente.getEndereco());
        }
}
