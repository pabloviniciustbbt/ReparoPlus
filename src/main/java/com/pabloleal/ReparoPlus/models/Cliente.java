package com.pabloleal.ReparoPlus.models;

import com.pabloleal.ReparoPlus.dto.ClienteCreateRequestDTO;
import com.pabloleal.ReparoPlus.dto.ClienteUpdateRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clientes")
@AllArgsConstructor
public class Cliente extends Pessoa{

    @Embedded
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<OrdemServico> ordensServico = new HashSet<>();

    public Cliente(){}

    public Cliente(ClienteCreateRequestDTO clienteDTO){
        super(formatarCpf(clienteDTO.cpf()), clienteDTO.nome(), clienteDTO.email(), clienteDTO.telefone());
        this.endereco = clienteDTO.endereco();
    }

    public void atualizarCliente(ClienteUpdateRequestDTO clienteDTO) {

        if (clienteDTO.cpf() != null){
            this.setCpf(Pessoa.formatarCpf(clienteDTO.cpf()));
        }
        if (clienteDTO.nome() != null){
            this.setNome(clienteDTO.nome());
        }
        if (clienteDTO.email() != null){
            this.setEmail(clienteDTO.email());
        }
        if (clienteDTO.telefone() != null){
            this.setTelefone(clienteDTO.telefone());
        }
        if (clienteDTO.endereco() != null){
            this.endereco.atualizarEndereco(clienteDTO.endereco());
        }
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Set<OrdemServico> getOrdensServico() {
        return ordensServico;
    }

    public void setOrdensServico(Set<OrdemServico> ordensServico) {
        this.ordensServico = ordensServico;
    }
}
