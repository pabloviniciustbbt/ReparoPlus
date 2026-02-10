package com.pabloleal.ReparoPlus.models;

import com.pabloleal.ReparoPlus.dto.produto.ProdutoCreateRequestDTO;
import com.pabloleal.ReparoPlus.dto.produto.ProdutoUpdateRequestDTO;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table (name = "produtos")
public class Produto {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private String codigoEan;
    private String fabricante;
    private BigDecimal precoCusto;
    private BigDecimal precoVenda;
    @ManyToOne
    @JoinColumn(name = "ordemServico_id")
    private OrdemServico ordemServico;
    private boolean ativo = true;

    public Produto() {
    }

    public Produto(Long id, String nome, String descricao, String codigoEan, String fabricante, BigDecimal precoCusto, BigDecimal precoVenda, OrdemServico ordemServico, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.codigoEan = codigoEan;
        this.fabricante = fabricante;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.ordemServico = ordemServico;
        this.ativo = ativo;
    }

    public Produto(ProdutoCreateRequestDTO produtoCreateRequestDTO) {
        this.nome = produtoCreateRequestDTO.nome();
        this.descricao = produtoCreateRequestDTO.descricao();
        this.codigoEan = produtoCreateRequestDTO.codigoEan();
        this.fabricante = produtoCreateRequestDTO.fabricante();
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

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
