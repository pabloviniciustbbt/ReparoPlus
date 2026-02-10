package com.pabloleal.ReparoPlus.dto.pessoa;

import com.pabloleal.ReparoPlus.models.Pessoa;
import jakarta.validation.constraints.Email;
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
        public PessoaUpdateRequestDTO(Pessoa pessoa) {
                this(
                        pessoa.getId(),
                        pessoa.getCpf(),
                        pessoa.getNome(),
                        pessoa.getEmail(),
                        pessoa.getTelefone()
                );
        }

}
