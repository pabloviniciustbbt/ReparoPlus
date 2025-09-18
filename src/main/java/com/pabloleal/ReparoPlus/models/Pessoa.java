package com.pabloleal.ReparoPlus.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@MappedSuperclass
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    @CPF(message = "CPF Inválido")
    private String cpf;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @Email
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String telefone;
}
