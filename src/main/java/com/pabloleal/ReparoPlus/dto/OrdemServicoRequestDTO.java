package com.pabloleal.ReparoPlus.dto;

import com.pabloleal.ReparoPlus.models.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrdemServicoRequestDTO(

        @NotNull
        String cpfCliente,
        @NotNull
        @Valid
        Equipamento equipamento,
        @NotNull
        Long atendenteId,
        @NotNull
        Long tecnicoId,
        StatusOS statusOS,
        String observacoesTecnicas,
        String observacoesOrdemServico

) {
}
