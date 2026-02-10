package com.pabloleal.ReparoPlus.dto.produto;

import com.pabloleal.ReparoPlus.models.Produto;
import java.math.BigDecimal;

public record ProdutoResumoResponseDTO(
        Long id,
        String nome,
        String descricao,
        String codigoEan,
        String fabricante,
        BigDecimal precoVenda
) {
    public ProdutoResumoResponseDTO(Produto produto) {
        this(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getCodigoEan(),
                produto.getFabricante(),
                produto.getPrecoVenda()
        );
    }
}
