package com.pabloleal.ReparoPlus.dto.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoCreateRequestDTO(
        @NotBlank
        String nome,
        @NotBlank
        String descricao,
        @NotBlank
        String codigoEan,
        @NotBlank
        String fabricante,
        @NotNull
        BigDecimal precoCusto,
        @NotNull
        BigDecimal precoVenda
) {
}
