package com.pabloleal.ReparoPlus.services;

import com.pabloleal.ReparoPlus.dto.AtendenteCreateRequestDTO;
import com.pabloleal.ReparoPlus.models.Atendente;
import com.pabloleal.ReparoPlus.repositories.AtendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtendenteServices {

    @Autowired
    private AtendenteRepository atendenteRepository;

    public AtendenteCreateRequestDTO cadastrarAtendente(AtendenteCreateRequestDTO dados) {
        Atendente atendente = new Atendente(dados);
        atendenteRepository.save(atendente);
        return dados;
    }
}
