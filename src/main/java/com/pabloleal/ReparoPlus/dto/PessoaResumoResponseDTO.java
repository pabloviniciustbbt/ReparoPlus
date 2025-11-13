package com.pabloleal.ReparoPlus.dto;

public record PessoaResumoResponseDTO(

        Long id,
        String nome

) {
    public PessoaResumoResponseDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
