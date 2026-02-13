package com.pabloleal.ReparoPlus.dto.servico;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ServicoUpdateRequestDTO(
        @NotNull
        Long id,
        String nome,
        BigDecimal valor
) {
}
