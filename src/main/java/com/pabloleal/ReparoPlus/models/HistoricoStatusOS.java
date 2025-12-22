package com.pabloleal.ReparoPlus.models;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Entity
@Table(name = "historico_statusOS")
public class HistoricoStatusOS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ordemServico_id")
    private OrdemServico ordemServico;
    @ManyToOne
    @JoinColumn(name = "atendente_id")
    private Atendente atendente;
    @Enumerated(EnumType.STRING)
    private StatusOS statusOS;
    private OffsetDateTime dataHoraAlteracao = OffsetDateTime.now(ZoneOffset.UTC);

    public HistoricoStatusOS() {
    }

    public HistoricoStatusOS(OrdemServico ordemServico, Atendente atendente, int statusOS) {
        this.ordemServico = ordemServico;
        this.atendente = atendente;
        this.statusOS = StatusOS.fromId(statusOS);

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public StatusOS getStatusOS() {
        return statusOS;
    }

    public void setStatusOS(StatusOS statusOS) {
        this.statusOS = statusOS;
    }

    public OffsetDateTime getDataHoraAlteracao() {
        return dataHoraAlteracao;
    }

    public void setDataHoraAlteracao(OffsetDateTime dataHoraAlteracao) {
        this.dataHoraAlteracao = dataHoraAlteracao;
    }
}
