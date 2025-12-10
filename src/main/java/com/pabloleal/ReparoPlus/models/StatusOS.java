package com.pabloleal.ReparoPlus.models;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StatusOS {

    EM_ORCAMENTO(1, "Em Orçamento"),
    NAO_APRESENTOU_DEFEITO(2, "Não Apresentou Defeito"),
    AGUARDANDO_APROVACAO(3, "Aguardando Aprovação"),
    ORCAMENTO_NAO_APROVADO(4, "Orçamento não Aprovado"),
    ORCAMENTO_APROVADO(5, "Orçamento Aprovado"),
    AGUARDANDO_PECA(6, "Aguardando Peça"),
    EM_EXECUCAO(7, "Em Execução"),
    DEVOLUCAO_SEM_CONSERTO(8, "Devolução sem Conserto"),
    CLIENTE_AVISADO_DE_PRONTO(9, "Cliente Avisado de Pronto"),
    EQUIPAMENTO_RETIRADO(10, "Equipamento Retirado");

    private final int id;
    private final String descricao;

    StatusOS(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }

    @JsonCreator
    public static StatusOS fromId(int id){
        StatusOS[] list = StatusOS.values();

        for (int i = 0; i < list.length; i++) {
            StatusOS statusOS = list[i];
            if (statusOS.id == id){
                return statusOS;
            }
        }
        throw new IllegalArgumentException("Status OS Inválido: " + id);
    }
}
