package com.pabloleal.ReparoPlus.dto;

import com.pabloleal.ReparoPlus.models.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteUpdateRequestDTO(

        @NotBlank
        String cpf,
        String nome,
        @Email
        String email,
        String telefone,
        EnderecoUpdateDTO endereco

) {
        public ClienteUpdateRequestDTO(Cliente cliente){
                this(
                        cliente.getCpf(),
                        cliente.getNome(),
                        cliente.getEmail(),
                        cliente.getTelefone(),
                        new EnderecoUpdateDTO(cliente.getEndereco()));
        }
}
