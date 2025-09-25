package com.pabloleal.ReparoPlus.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "atendentes")
@NoArgsConstructor
public class Atendente extends Pessoa {

    @OneToMany(mappedBy = "atendente", fetch = FetchType.LAZY)
    private Set<OrdemServico> ordensServico = new HashSet<>();

    public Atendente(String cpf, String nome, String email, String telefone, Set<OrdemServico> ordensServico) {
        super(cpf, nome, email, telefone);
        this.ordensServico = ordensServico;
    }

    public Set<OrdemServico> getOrdensServico() {
        return ordensServico;
    }

    public void setOrdensServico(Set<OrdemServico> ordensServico) {
        this.ordensServico = ordensServico;
    }
}
