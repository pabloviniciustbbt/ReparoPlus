package com.pabloleal.ReparoPlus.dto.produto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ProdutoUpdateRequestDTO(
        @NotNull
        Long id,
        String nome,
        String descricao,
        String codigoEan,
        String fabricante,
        BigDecimal precoCusto,
        BigDecimal precoVenda
) {
}
