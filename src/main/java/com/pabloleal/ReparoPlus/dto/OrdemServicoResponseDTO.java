package com.pabloleal.ReparoPlus.dto;

import com.pabloleal.ReparoPlus.models.*;

import java.time.OffsetDateTime;

public record OrdemServicoResponseDTO(

        Long id,
        Cliente cliente,
        Equipamento equipamento,
        Atendente atendente,
        Tecnico tecnico,
        StatusOS statusOS,
        String observacoesTecnicas,
        String observacoesOrdemServico,
        OffsetDateTime dataHoraAbertura

) {
}
