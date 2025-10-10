package com.pabloleal.ReparoPlus.services;

import com.pabloleal.ReparoPlus.dto.PessoaCreateRequestDTO;
import com.pabloleal.ReparoPlus.dto.PessoaUpdateRequestDTO;
import com.pabloleal.ReparoPlus.models.Tecnico;
import com.pabloleal.ReparoPlus.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TecnicoServices {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public PessoaCreateRequestDTO cadastrarTecnico(PessoaCreateRequestDTO dados) {
        Tecnico tecnico = new Tecnico(dados);
        tecnicoRepository.save(tecnico);
        return dados;
    }

    public PessoaUpdateRequestDTO atualizarTecnico(PessoaUpdateRequestDTO dados) {
        Tecnico tecnico = tecnicoRepository.getReferenceById(dados.id());
        tecnico.atualizarPessoa(dados);
        return new PessoaUpdateRequestDTO(tecnico);
    }

    public void deletarTecnico(Long id) {
        Tecnico tecnico = tecnicoRepository.getReferenceById(id);
        tecnico.deletarPessoa();
    }
}
