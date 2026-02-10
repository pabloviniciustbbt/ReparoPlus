package com.pabloleal.ReparoPlus.models;

import com.pabloleal.ReparoPlus.dto.pessoa.PessoaCreateRequestDTO;
import com.pabloleal.ReparoPlus.dto.pessoa.PessoaUpdateRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@MappedSuperclass
@AllArgsConstructor
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

    private Boolean ativo;

    public Pessoa() {}

    protected Pessoa(String cpf, String nome, String email, String telefone) {
        this.cpf = formatarCpf(cpf);
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.ativo = true;
    }

    public Pessoa(PessoaCreateRequestDTO pessoaDTO) {
        this.cpf = formatarCpf(pessoaDTO.cpf());
        this.nome = pessoaDTO.nome();
        this.email = pessoaDTO.email();
        this.telefone = pessoaDTO.telefone();
        this.ativo = true;
    }

    public void atualizarPessoa(PessoaUpdateRequestDTO pessoaDTO) {
        if (pessoaDTO.cpf() != null){
            this.cpf = formatarCpf(pessoaDTO.cpf());
        }
        if (pessoaDTO.nome() != null){
            this.nome = pessoaDTO.nome();
        }
        if (pessoaDTO.email() != null){
            this.email = pessoaDTO.email();
        }
        if (pessoaDTO.telefone() != null){
            this.telefone = pessoaDTO.telefone();
        }
    }

    public void deletarPessoa(){
        this.ativo = false;
    }
    public void ativarPessoa(){
        this.ativo = true;
    }
    public static String formatarCpf(String cpf){
        cpf = cpf.replaceAll("\\.","");
        cpf = cpf.replaceAll("-","");
        return cpf;
    }

    public Long getId() {
        return id;
    }
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }


}
