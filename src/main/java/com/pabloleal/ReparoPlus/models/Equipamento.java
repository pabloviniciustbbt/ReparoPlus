package com.pabloleal.ReparoPlus.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Equipamento {

    @NotBlank
    @Column(nullable = false)
    private String tipoEquipamento;

    @NotBlank
    @Column(nullable = false)
    private String marca;

    @NotBlank
    @Column(nullable = false)
    private String modelo;

    @NotBlank
    @Column(nullable = false)
    private String numeroSerie;

    @NotBlank
    @Column(nullable = false)
    private String acessorios;

    @NotBlank
    @Column(nullable = false)
    private String defeitoRelatado;

    @NotBlank
    @Column(nullable = false)
    private String estadoFisico;
}
