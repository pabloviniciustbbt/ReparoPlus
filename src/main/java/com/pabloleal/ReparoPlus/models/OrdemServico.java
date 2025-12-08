package com.pabloleal.ReparoPlus.models;

import com.pabloleal.ReparoPlus.dto.OrdemServicoUpdateRequestDTO;
import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Entity
@Table(name = "ordens_servico")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Embedded
    private Equipamento equipamento;

    @ManyToOne(optional = false)
    @JoinColumn(name = "atendente_id")
    private Atendente atendente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

    @Enumerated(EnumType.STRING)
    private StatusOS statusOS;

    private String observacoesTecnicas;

    private String observacoesOrdemServico;

    private OffsetDateTime dataHoraAbertura = OffsetDateTime.now(ZoneOffset.UTC);
    private boolean ativo = true;

    public OrdemServico() {
    }

    public OrdemServico(Cliente cliente, Equipamento equipamento, Atendente atendente, Tecnico tecnico, StatusOS statusOS, String observacoesTecnicas, String observacoesOrdemServico) {
        this.cliente = cliente;
        this.equipamento = equipamento;
        this.atendente = atendente;
        this.tecnico = tecnico;
        this.statusOS = statusOS;
        this.observacoesTecnicas = observacoesTecnicas;
        this.observacoesOrdemServico = observacoesOrdemServico;
    }

    public void atualizarOrdemServico(OrdemServicoUpdateRequestDTO dados) {

        if (dados.equipamento() != null){
            atualizarEquipamento(dados.equipamento());
        }
        if (dados.statusOS() != null){
            this.statusOS = dados.statusOS();
        }
        if (dados.observacoesTecnicas() != null){
            this.observacoesTecnicas = dados.observacoesTecnicas();
        }
        if (dados.observacoesOrdemServico() != null){
            this.observacoesOrdemServico = dados.observacoesOrdemServico();
        }
    }

    private void atualizarEquipamento(Equipamento equipamento){

        if (equipamento.getTipoEquipamento() != null){
            this.equipamento.setTipoEquipamento(equipamento.getTipoEquipamento());
        }
        if (equipamento.getMarca() != null){
            this.equipamento.setMarca(equipamento.getMarca());
        }
        if (equipamento.getModelo() != null){
            this.equipamento.setModelo(equipamento.getModelo());
        }
        if (equipamento.getNumeroSerie() != null){
            this.equipamento.setNumeroSerie(equipamento.getNumeroSerie());
        }
        if (equipamento.getAcessorios() != null){
            this.equipamento.setAcessorios(equipamento.getAcessorios());
        }
        if (equipamento.getDefeitoRelatado() != null){
            this.equipamento.setDefeitoRelatado(equipamento.getDefeitoRelatado());
        }
        if (equipamento.getEstadoFisico() != null){
            this.equipamento.setEstadoFisico(equipamento.getEstadoFisico());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public StatusOS getStatusOS() {
        return statusOS;
    }

    public void setStatusOS(StatusOS statusOS) {
        this.statusOS = statusOS;
    }

    public String getObservacoesTecnicas() {
        return observacoesTecnicas;
    }

    public void setObservacoesTecnicas(String observacoesTecnicas) {
        this.observacoesTecnicas = observacoesTecnicas;
    }

    public String getObservacoesOrdemServico() {
        return observacoesOrdemServico;
    }

    public void setObservacoesOrdemServico(String observacoesOrdemServico) {
        this.observacoesOrdemServico = observacoesOrdemServico;
    }

    public OffsetDateTime getDataHoraAbertura() {
        return dataHoraAbertura;
    }

    public void setDataHoraAbertura(OffsetDateTime dataHoraAbertura) {
        this.dataHoraAbertura = dataHoraAbertura;
    }
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }


    public void cancelarOrdemServico() {
        this.ativo = false;
    }
}
