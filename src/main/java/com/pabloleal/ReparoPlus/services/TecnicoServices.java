package com.pabloleal.ReparoPlus.services;

import com.pabloleal.ReparoPlus.dto.PessoaCreateRequestDTO;
import com.pabloleal.ReparoPlus.dto.PessoaResponseDTO;
import com.pabloleal.ReparoPlus.dto.PessoaUpdateRequestDTO;
import com.pabloleal.ReparoPlus.exceptions.EntidadeAtivaInativaException;
import com.pabloleal.ReparoPlus.exceptions.EntidadeCadastradaException;
import com.pabloleal.ReparoPlus.models.Pessoa;
import com.pabloleal.ReparoPlus.models.Tecnico;
import com.pabloleal.ReparoPlus.repositories.TecnicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TecnicoServices {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico cadastrarTecnico(PessoaCreateRequestDTO dados) {

        String cpfFormatado = Pessoa.formatarCpf(dados.cpf());

        if (tecnicoRepository.existsByCpf(cpfFormatado)) {
            throw new EntidadeCadastradaException("Tecnico com CPF " + cpfFormatado + " já cadastrado");
        }

        Tecnico tecnico = new Tecnico(dados);
        tecnicoRepository.save(tecnico);
        return tecnico;
    }

    public Tecnico atualizarTecnico(PessoaUpdateRequestDTO dados) {
        Tecnico tecnico = tecnicoRepository.findById(dados.id()).
                orElseThrow(() -> new EntityNotFoundException("Tecnico com ID " + dados.id() + " não encontrado"));

        if (dados.cpf() != null) {
            String cpfFormatado = Pessoa.formatarCpf(dados.cpf());

            if (tecnicoRepository.existsByCpf(cpfFormatado)) {
                throw new EntidadeCadastradaException("Tecnico com CPF " + cpfFormatado + " já cadastrado");
            }
        }

        tecnico.atualizarPessoa(dados);
        return tecnico;
    }

    public void deletarTecnico(Long id) {
        Tecnico tecnico = tecnicoRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Tecnico com ID " + id + " não encontrado"));

        if (!tecnico.getAtivo()) {
            throw new EntidadeAtivaInativaException("Tecnico com ID " + id + " já está inativo");
        }

        tecnico.deletarPessoa();
    }

    public Tecnico ativarTecnico(Long id) {
        Tecnico tecnico = tecnicoRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Tecnico com ID " + id + " não encontrado"));

        if (tecnico.getAtivo()) {
            throw new EntidadeAtivaInativaException("Tecnico com ID " + id + " já está ativo");
        }

        tecnico.ativarPessoa();
        return tecnico;
    }

    public Tecnico buscarTecnicoID(Long id) {
        Tecnico tecnico = tecnicoRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Tecnico com ID " + id + " não encontrado"));

        return tecnico;
    }

    public Tecnico buscarTecnicoCPF(String cpf) {

        String cpfFormatado = Pessoa.formatarCpf(cpf);

        Tecnico tecnico = tecnicoRepository.findByCpf(cpfFormatado).
                orElseThrow(() -> new EntityNotFoundException("Tecnico com ID " + cpfFormatado + " não encontrado"));

        return tecnico;
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
