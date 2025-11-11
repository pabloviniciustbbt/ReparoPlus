package com.pabloleal.ReparoPlus.dto;

import com.pabloleal.ReparoPlus.models.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrdemServicoRequestDTO(

        @NotBlank(message = "CPF do Cliente é obrigatório")
        String cpfCliente,
        @NotNull(message = "Cadastro do Equipamento é obrigatório")
        @Valid
        Equipamento equipamento,
        @NotNull(message = "ID do Atendente é obrigatório")
        Long atendenteId,
        @NotNull(message = "ID do Técnico é obrigatório")
        Long tecnicoId,
        StatusOS statusOS,
        String observacoesTecnicas,
        String observacoesOrdemServico

) {
        public OrdemServicoRequestDTO {
                if (statusOS == null){
                        statusOS = StatusOS.EM_ORCAMENTO;
                }
        }
}
