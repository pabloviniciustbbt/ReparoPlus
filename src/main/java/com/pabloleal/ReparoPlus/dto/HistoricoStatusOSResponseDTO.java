package com.pabloleal.ReparoPlus.dto;

import com.pabloleal.ReparoPlus.models.HistoricoStatusOS;
import com.pabloleal.ReparoPlus.models.StatusOS;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public record HistoricoStatusOSResponseDTO(
        Long id,
        String atendente,
        StatusOS statusOS,
        String dataHoraAlteracao

) {
    public HistoricoStatusOSResponseDTO(HistoricoStatusOS historicoStatusOS) {
        this(
                historicoStatusOS.getId(),
                historicoStatusOS.getAtendente().getNome(),
                historicoStatusOS.getStatusOS(),
                formataDataHora(historicoStatusOS.getDataHoraAlteracao())
        );
    }

    public static String formataDataHora(OffsetDateTime dataHora){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return dataHora.atZoneSameInstant(ZoneId.of("America/Sao_Paulo")).format(formatter);
    }
}
