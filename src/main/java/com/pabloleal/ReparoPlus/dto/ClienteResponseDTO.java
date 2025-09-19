package com.pabloleal.ReparoPlus.dto;

import com.pabloleal.ReparoPlus.models.Endereco;

public record ClienteResponseDTO(

        Long id,
        String cpf,
        String nome,
        String email,
        String telefone,
        Endereco endereco

){
}
