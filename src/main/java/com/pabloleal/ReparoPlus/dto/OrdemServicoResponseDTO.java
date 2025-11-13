package com.pabloleal.ReparoPlus.dto;

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
}
