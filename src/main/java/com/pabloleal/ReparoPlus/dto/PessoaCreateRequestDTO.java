package com.pabloleal.ReparoPlus.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PessoaCreateRequestDTO(

        @NotBlank
        String cpf,
        @NotBlank
        String nome,
        @Email
        String email,
        @NotBlank
        String telefone

        ) {
}
