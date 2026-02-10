package com.pabloleal.ReparoPlus.dto.ordemServico;

import com.pabloleal.ReparoPlus.dto.cliente.ClienteResponseDTO;
import com.pabloleal.ReparoPlus.dto.pessoa.PessoaResumoResponseDTO;
import com.pabloleal.ReparoPlus.models.*;

import java.time.OffsetDateTime;

public record OrdemServicoResponseDTO(

        Long id,
        ClienteResponseDTO cliente,
        Equipamento equipamento,
        PessoaResumoResponseDTO atendente,
        PessoaResumoResponseDTO tecnico,
        StatusOS statusOS,
        String observacoesTecnicas,
        String observacoesOrdemServico,
        OffsetDateTime dataHoraAbertura

) {
    public OrdemServicoResponseDTO(OrdemServico ordemServico) {
        this(
                ordemServico.getId(),
                new ClienteResponseDTO(ordemServico.getCliente()),
                ordemServico.getEquipamento(),
                new PessoaResumoResponseDTO(ordemServico.getAtendente()),
                new PessoaResumoResponseDTO(ordemServico.getTecnico()),
                ordemServico.getStatusOS(),
                ordemServico.getObservacoesTecnicas(),
                ordemServico.getObservacoesOrdemServico(),
                ordemServico.getDataHoraAbertura()
        );
    }
}
