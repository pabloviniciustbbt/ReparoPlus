package com.pabloleal.ReparoPlus.dto.produto;

import com.pabloleal.ReparoPlus.models.Produto;
import java.math.BigDecimal;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        String descricao,
        String codigoEan,
        String fabricante,
        BigDecimal precoCusto,
        BigDecimal precoVenda
) {
    public ProdutoResponseDTO(Produto produto) {
        this(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getCodigoEan(),
                produto.getFabricante(),
                produto.getPrecoCusto(),
                produto.getPrecoVenda()
        );
    }
}
