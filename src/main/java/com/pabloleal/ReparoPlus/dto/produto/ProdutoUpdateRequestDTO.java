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
        Integer quantidadeEstoque,
        BigDecimal precoCusto,
        BigDecimal precoVenda
) {
}
