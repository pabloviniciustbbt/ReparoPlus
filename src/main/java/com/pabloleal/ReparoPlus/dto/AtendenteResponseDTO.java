package com.pabloleal.ReparoPlus.dto;

public record AtendenteResponseDTO(

        Long id,
        String cpf,
        String nome,
        String email,
        String telefone

) {
}
