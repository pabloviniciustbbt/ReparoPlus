package com.pabloleal.ReparoPlus.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Entity
@Table(name = "ordens_servico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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


}
