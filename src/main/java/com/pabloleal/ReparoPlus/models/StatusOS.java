package com.pabloleal.ReparoPlus.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;


public enum StatusOS {

    EM_ORCAMENTO("Em Orçamento"),
    NAO_APRESENTOU_DEFEITO("Não Apresentou Defeito"),
    AGUARDANDO_APROVACAO("Aguardando Aprovação"),
    ORCAMENTO_NAO_APROVADO("Orçamento não Aprovado"),
    ORCAMENTO_APROVADO("Orçamento Aprovado"),
    AGUARDANDO_PECA("Aguardando Peça"),
    EM_EXECUCAO("Em Execução"),
    DEVOLUCAO_SEM_CONSERTO("Devolução sem Conserto"),
    CLIENTE_AVISADO_DE_PRONTO("Cliente Avisado de Pronto"),
    EQUIPAMENTO_RETIRADO("Equipamento Retirado");

    private final String descricao;

    StatusOS(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
