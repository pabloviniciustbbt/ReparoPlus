package com.pabloleal.ReparoPlus.services;

import com.pabloleal.ReparoPlus.dto.PessoaCreateRequestDTO;
import com.pabloleal.ReparoPlus.dto.PessoaUpdateRequestDTO;
import com.pabloleal.ReparoPlus.models.Atendente;
import com.pabloleal.ReparoPlus.repositories.AtendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtendenteServices {

    @Autowired
    private AtendenteRepository atendenteRepository;

    public PessoaCreateRequestDTO cadastrarAtendente(PessoaCreateRequestDTO dados) {
        Atendente atendente = new Atendente(dados);
        atendenteRepository.save(atendente);
        return dados;
    }

    public PessoaUpdateRequestDTO atualizarAtendente(PessoaUpdateRequestDTO dados) {
        Atendente atendente = atendenteRepository.getReferenceById(dados.id());
        atendente.atualizarPessoa(dados);
        return new PessoaUpdateRequestDTO(atendente);
    }

    public void deletarAtendente(Long id) {
        Atendente atendente = atendenteRepository.getReferenceById(id);
        atendente.deletarPessoa();
    }
}
