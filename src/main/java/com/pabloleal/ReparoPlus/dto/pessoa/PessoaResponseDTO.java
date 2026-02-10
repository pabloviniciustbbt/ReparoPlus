package com.pabloleal.ReparoPlus.dto.pessoa;

import com.pabloleal.ReparoPlus.models.Atendente;
import com.pabloleal.ReparoPlus.models.Tecnico;

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

    public PessoaResponseDTO(Atendente atendente){
        this(
                atendente.getId(),
                atendente.getCpf(),
                atendente.getNome(),
                atendente.getEmail(),
                atendente.getTelefone()

        );
    }

    public PessoaResponseDTO(Tecnico tecnico){
        this(
                tecnico.getId(),
                tecnico.getCpf(),
                tecnico.getNome(),
                tecnico.getEmail(),
                tecnico.getTelefone()

        );
    }
}
