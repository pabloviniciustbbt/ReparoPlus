package com.pabloleal.ReparoPlus.dto;

import com.pabloleal.ReparoPlus.models.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteRequestDTO(

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
}
