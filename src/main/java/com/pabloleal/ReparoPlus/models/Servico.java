package com.pabloleal.ReparoPlus.models;

import com.pabloleal.ReparoPlus.dto.servico.ServicoCreateRequestDTO;
import com.pabloleal.ReparoPlus.dto.servico.ServicoUpdateRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "servicos")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotNull
    private BigDecimal valor;
    private Boolean ativo = true;

    public Servico() {
    }

    public Servico(ServicoCreateRequestDTO servicoCreateRequestDTO) {
        this.nome = servicoCreateRequestDTO.nome();
        this.valor = servicoCreateRequestDTO.valor();
    }

    public Servico(Long id, String nome, BigDecimal valor, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.ativo = ativo;
    }

    public void atualizarServico(ServicoUpdateRequestDTO servicoUpdateRequestDTO) {
        if (servicoUpdateRequestDTO.nome() != null) {
            this.nome = servicoUpdateRequestDTO.nome();
        }
        if (servicoUpdateRequestDTO.valor() != null) {
            this.valor = servicoUpdateRequestDTO.valor();
        }
    }

    public void deletarServico() {
        this.ativo = false;
    }

    public void ativarServico() {
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }


}
