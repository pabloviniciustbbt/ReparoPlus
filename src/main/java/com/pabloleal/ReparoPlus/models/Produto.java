package com.pabloleal.ReparoPlus.models;

import com.pabloleal.ReparoPlus.dto.produto.ProdutoCreateRequestDTO;
import com.pabloleal.ReparoPlus.dto.produto.ProdutoUpdateRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table (name = "produtos")
public class Produto {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @NotBlank
    private String codigoEan;
    @NotBlank
    private String fabricante;
    @NotNull
    private Integer quantidadeEstoque;
    @NotNull
    private BigDecimal precoCusto;
    @NotNull
    private BigDecimal precoVenda;
    private boolean ativo = true;

    public Produto() {
    }

    public Produto(Long id, String nome, String descricao, String codigoEan, String fabricante, Integer quantidadeEstoque, BigDecimal precoCusto, BigDecimal precoVenda, OrdemServico ordemServico, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.codigoEan = codigoEan;
        this.fabricante = fabricante;
        this.quantidadeEstoque = quantidadeEstoque;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.ativo = ativo;
    }

    public Produto(ProdutoCreateRequestDTO produtoCreateRequestDTO) {
        this.nome = produtoCreateRequestDTO.nome();
        this.descricao = produtoCreateRequestDTO.descricao();
        this.codigoEan = produtoCreateRequestDTO.codigoEan();
        this.fabricante = produtoCreateRequestDTO.fabricante();
        this.quantidadeEstoque = produtoCreateRequestDTO.quantidadeEstoque();
        this.quantidadeEstoque = produtoCreateRequestDTO.quantidadeEstoque();
        this.precoCusto = produtoCreateRequestDTO.precoCusto();
        this.precoVenda = produtoCreateRequestDTO.precoVenda();
    }

    public void atualizaProduto(ProdutoUpdateRequestDTO produtoUpdateRequestDTO) {
        if (produtoUpdateRequestDTO.nome() != null){
            this.nome = produtoUpdateRequestDTO.nome();
        }
        if (produtoUpdateRequestDTO.descricao() != null){
            this.descricao = produtoUpdateRequestDTO.descricao();
        }
        if (produtoUpdateRequestDTO.codigoEan() != null){
            this.codigoEan = produtoUpdateRequestDTO.codigoEan();
        }
        if (produtoUpdateRequestDTO.fabricante() != null){
            this.fabricante = produtoUpdateRequestDTO.fabricante();
        }
        if (produtoUpdateRequestDTO.quantidadeEstoque() != null){
            this.quantidadeEstoque = produtoUpdateRequestDTO.quantidadeEstoque();
        }
        if (produtoUpdateRequestDTO.precoCusto() != null){
            this.precoCusto = produtoUpdateRequestDTO.precoCusto();
        }
        if (produtoUpdateRequestDTO.precoVenda() != null){
            this.precoVenda = produtoUpdateRequestDTO.precoVenda();
        }
    }

    public void deletarProduto() {
        this.ativo = false;
    }

    public void ativarProduto() {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigoEan() {
        return codigoEan;
    }

    public void setCodigoEan(String codigoEan) {
        this.codigoEan = codigoEan;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public BigDecimal getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(BigDecimal precoCusto) {
        this.precoCusto = precoCusto;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
