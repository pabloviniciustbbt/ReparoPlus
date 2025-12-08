package com.pabloleal.ReparoPlus.services;

import com.pabloleal.ReparoPlus.dto.PessoaCreateRequestDTO;
import com.pabloleal.ReparoPlus.dto.PessoaResponseDTO;
import com.pabloleal.ReparoPlus.dto.PessoaUpdateRequestDTO;
import com.pabloleal.ReparoPlus.models.Atendente;
import com.pabloleal.ReparoPlus.repositories.AtendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

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

    public PessoaResponseDTO buscarAtendenteID(Long id) {
        Optional<Atendente> atendente = atendenteRepository.findById(id);

        if (atendente.isPresent()){
            Atendente a = atendente.get();
            return new PessoaResponseDTO(a.getId(), a.getCpf(), a.getNome(), a.getEmail(), a.getTelefone());
        }

        return null;
    }

    public PessoaResponseDTO buscarAtendenteCPF(String cpf) {
        Optional<Atendente> atendente = atendenteRepository.findByCpf(cpf);

        if (atendente.isPresent()){
            Atendente a = atendente.get();
            return new PessoaResponseDTO(a.getId(), a.getCpf(), a.getNome(), a.getEmail(), a.getTelefone());
        }

        return null;
    }

    public Page<PessoaResponseDTO> listarAtendentes(Pageable pageable) {
        return atendenteRepository.findAll(pageable)
                .map(PessoaResponseDTO::new);
    }

    public Page<PessoaResponseDTO> listarAtendentesAtivos(Pageable pageable) {
        return atendenteRepository.findAllByAtivoTrue(pageable)
                .map(PessoaResponseDTO::new);
    }

    public Page<PessoaResponseDTO> listarAtendentesInativos(Pageable pageable) {
        return atendenteRepository.findAllByAtivoFalse(pageable)
                .map(PessoaResponseDTO::new);
    }
}
