package com.pabloleal.ReparoPlus.dto;

import com.pabloleal.ReparoPlus.models.Endereco;
import com.pabloleal.ReparoPlus.models.UF;
import jakarta.validation.constraints.Pattern;
import org.jetbrains.annotations.NotNull;

public record EnderecoUpdateDTO(

        String logradouro,

        String numero,

        String complemento,

        String bairro,

        String cidade,

        @Pattern(regexp = "\\d{5}-?\\d{3}")
        String cep,

        UF uf
) {
        public EnderecoUpdateDTO(Endereco endereco) {
                this(
                        endereco.getLogradouro(),
                        endereco.getNumero(),
                        endereco.getComplemento(),
                        endereco.getBairro(),
                        endereco.getCidade(),
                        endereco.getCep(),
                        endereco.getUf()
                );
        }
}
