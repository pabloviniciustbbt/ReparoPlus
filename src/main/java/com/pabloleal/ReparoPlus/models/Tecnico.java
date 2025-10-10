package com.pabloleal.ReparoPlus.models;

import com.pabloleal.ReparoPlus.dto.PessoaCreateRequestDTO;
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
@Table(name = "tecnicos")
public class Tecnico extends Pessoa {

    @OneToMany(mappedBy = "tecnico", fetch = FetchType.LAZY)
    private Set<OrdemServico> ordensServico = new HashSet<>();

    public Tecnico() {
    }

    public Tecnico(String cpf, String nome, String email, String telefone) {
        super(cpf, nome, email, telefone);
    }

    public Tecnico(PessoaCreateRequestDTO dados) {
        super(dados);
    }

    public Set<OrdemServico> getOrdensServico() {
        return ordensServico;
    }

    public void setOrdensServico(Set<OrdemServico> ordensServico) {
        this.ordensServico = ordensServico;
    }
}
