package com.pabloleal.ReparoPlus.dto;

import com.pabloleal.ReparoPlus.models.Equipamento;
import com.pabloleal.ReparoPlus.models.OrdemServico;
import com.pabloleal.ReparoPlus.models.StatusOS;

import java.time.OffsetDateTime;

public record DadosListagemOrdemServicoDTO(
        Long id,
        ClienteResponseDTO cliente,
        Equipamento equipamento,
        PessoaResumoResponseDTO atendente,
        PessoaResumoResponseDTO tecnico,
        StatusOS statusOS,
        OffsetDateTime dataHoraAbertura
) {
    public DadosListagemOrdemServicoDTO(OrdemServico ordemServico) {
        this(
                ordemServico.getId(),
                new ClienteResponseDTO(ordemServico.getCliente()),
                ordemServico.getEquipamento(),
                new PessoaResumoResponseDTO(ordemServico.getAtendente()),
                new PessoaResumoResponseDTO(ordemServico.getTecnico()),
                ordemServico.getStatusOS(),
                ordemServico.getDataHoraAbertura()
        );
    }
}
