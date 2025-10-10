package com.pabloleal.ReparoPlus.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PessoaUpdateRequestDTO(
        @NotNull
        Long id,
        String cpf,
        String nome,
        @Email
        String email,
        String telefone

        ) {
}
