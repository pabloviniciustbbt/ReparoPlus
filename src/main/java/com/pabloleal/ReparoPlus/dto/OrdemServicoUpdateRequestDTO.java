package com.pabloleal.ReparoPlus.dto;

import com.pabloleal.ReparoPlus.models.Equipamento;
import com.pabloleal.ReparoPlus.models.StatusOS;
import jakarta.validation.constraints.NotNull;

public record OrdemServicoUpdateRequestDTO(

        @NotNull
        Long id,
        String cpfCliente,
        Equipamento equipamento,
        Long atendenteId,
        Long tecnicoId,
        StatusOS statusOS,
        String observacoesTecnicas,
        String observacoesOrdemServico

) {
}
