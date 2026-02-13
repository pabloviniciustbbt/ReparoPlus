package com.pabloleal.ReparoPlus.dto.servico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ServicoCreateRequestDTO(
        @NotBlank
        String nome,
        @NotNull
        BigDecimal valor
) {
}
