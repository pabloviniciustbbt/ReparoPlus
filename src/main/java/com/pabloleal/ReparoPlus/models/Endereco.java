package com.pabloleal.ReparoPlus.models;

import com.pabloleal.ReparoPlus.dto.endereco.EnderecoUpdateDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Endereco {

    @NotBlank
    @Column(nullable = false)
    private String logradouro;

    @NotBlank
    @Column(nullable = false)
    private String numero;

    private String complemento;

    @NotBlank
    @Column(nullable = false)
    private String bairro;

    @NotBlank
    @Column(nullable = false)
    private String cidade;

    @NotBlank
    @Column(nullable = false)
    @Pattern(regexp = "\\d{5}-?\\d{3}")
    private String cep;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 2)
    private UF uf;

    public void atualizarEndereco(EnderecoUpdateDTO dados){
        if (dados.logradouro() != null){
            this.setLogradouro(dados.logradouro());
        }
        if (dados.numero() != null){
            this.setNumero(dados.numero());
        }
        if (dados.complemento() != null){
            this.setComplemento(dados.complemento());
        }
        if (dados.bairro() != null){
            this.setBairro(dados.bairro());
        }
        if (dados.cidade() != null){
            this.setCidade(dados.cidade());
        }
        if (dados.cep() != null){
            this.setCep(dados.cep());
        }
        if (dados.uf()!= null){
            this.setUf(dados.uf());
        }
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }
}
