package com.pabloleal.ReparoPlus.models;

import com.pabloleal.ReparoPlus.dto.AtendenteCreateRequestDTO;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "atendentes")
@NoArgsConstructor
public class Atendente extends Pessoa {

    @OneToMany(mappedBy = "atendente", fetch = FetchType.LAZY)
    private Set<OrdemServico> ordensServico = new HashSet<>();

    public Atendente(AtendenteCreateRequestDTO atendenteDTO) {
        super(atendenteDTO.cpf(), atendenteDTO.nome(), atendenteDTO.email(), atendenteDTO.telefone());
    }

    public Set<OrdemServico> getOrdensServico() {
        return ordensServico;
    }

    public void setOrdensServico(Set<OrdemServico> ordensServico) {
        this.ordensServico = ordensServico;
    }
}
