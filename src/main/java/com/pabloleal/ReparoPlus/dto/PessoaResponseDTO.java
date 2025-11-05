package com.pabloleal.ReparoPlus.dto;

public record PessoaResponseDTO(

        Long id,
        String cpf,
        String nome,
        String email,
        String telefone

) {
    public PessoaResponseDTO(Long id, String cpf, String nome, String email, String telefone) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }
}
