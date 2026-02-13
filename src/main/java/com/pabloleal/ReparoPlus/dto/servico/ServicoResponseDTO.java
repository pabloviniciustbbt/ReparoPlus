package com.pabloleal.ReparoPlus.dto.servico;

import com.pabloleal.ReparoPlus.models.Servico;
import java.math.BigDecimal;

public record ServicoResponseDTO(
        Long id,
        String nome,
        BigDecimal valor
) {
    public ServicoResponseDTO(Servico servico) {
        this(
                servico.getId(),
                servico.getNome(),
                servico.getValor()
        );
    }
}
