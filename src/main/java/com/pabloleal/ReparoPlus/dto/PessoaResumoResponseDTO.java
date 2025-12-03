package com.pabloleal.ReparoPlus.dto;

import com.pabloleal.ReparoPlus.models.Pessoa;

public record PessoaResumoResponseDTO(

        Long id,
        String nome

) {
    public PessoaResumoResponseDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public  PessoaResumoResponseDTO(Pessoa pessoa){
        this(
                pessoa.getId(),
                pessoa.getNome()
        );
    }
}
