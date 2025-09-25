package com.pabloleal.ReparoPlus.models;

import com.pabloleal.ReparoPlus.dto.ClienteCreateRequestDTO;
import com.pabloleal.ReparoPlus.dto.ClienteUpdateRequestDTO;
import com.pabloleal.ReparoPlus.dto.EnderecoUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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
        super(clienteDTO.cpf(), clienteDTO.nome(), clienteDTO.email(), clienteDTO.telefone());
        this.endereco = clienteDTO.endereco();
    }

    public void atualizarCliente(ClienteUpdateRequestDTO dados) {

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
            this.endereco.atualizarEndereco(dados.endereco());
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
