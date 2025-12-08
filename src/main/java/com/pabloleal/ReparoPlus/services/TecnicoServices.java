package com.pabloleal.ReparoPlus.services;

import com.pabloleal.ReparoPlus.dto.PessoaCreateRequestDTO;
import com.pabloleal.ReparoPlus.dto.PessoaResponseDTO;
import com.pabloleal.ReparoPlus.dto.PessoaUpdateRequestDTO;
import com.pabloleal.ReparoPlus.models.Tecnico;
import com.pabloleal.ReparoPlus.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public PessoaResponseDTO buscarTecnicoID(Long id) {
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);

        if (tecnico.isPresent()){
            Tecnico t = tecnico.get();
            return new PessoaResponseDTO(t.getId(), t.getCpf(), t.getNome(), t.getEmail(), t.getTelefone());
        }

        return null;
    }

    public PessoaResponseDTO buscarTecnicoCPF(String cpf){
        Optional<Tecnico> tecnico = tecnicoRepository.findByCpf(cpf);

        if (tecnico.isPresent()){
            Tecnico t = tecnico.get();
            return new PessoaResponseDTO(t.getId(), t.getCpf(), t.getNome(), t.getEmail(), t.getTelefone());
        }

        return null;
    }

    public Page<PessoaResponseDTO> listarTecnicos(Pageable pageable) {
        return tecnicoRepository.findAll(pageable)
                .map(PessoaResponseDTO::new);
    }

    public Page<PessoaResponseDTO> listarTecnicosAtivos(Pageable pageable) {
        return tecnicoRepository.findAllByAtivoTrue(pageable)
                .map(PessoaResponseDTO::new);
    }

    public Page<PessoaResponseDTO> listarTecnicosInativos(Pageable pageable) {
        return tecnicoRepository.findAllByAtivoFalse(pageable)
                .map(PessoaResponseDTO::new);
    }
}
