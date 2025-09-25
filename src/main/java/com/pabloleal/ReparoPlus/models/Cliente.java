package com.pabloleal.ReparoPlus.models;

import com.pabloleal.ReparoPlus.dto.ClienteRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clientes")
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends Pessoa{

    @Embedded
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<OrdemServico> ordensServico = new HashSet<>();

    public Cliente(ClienteRequestDTO clienteDTO){
        super(clienteDTO.cpf(), clienteDTO.nome(), clienteDTO.email(), clienteDTO.telefone());
        this.endereco = clienteDTO.endereco();
    }

    public void atualizarCliente(ClienteRequestDTO dados) {

        if (dados.nome() != null){
            this.setNome(dados.nome());
        }
        if (dados.email() != null){
            this.setEmail(dados.email());
        }

        if (dados.telefone() != null){
            this.setTelefone(dados.telefone());
        }
        if (dados.endereco() != null){
            this.setEndereco(dados.endereco());
        }

    }


    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
