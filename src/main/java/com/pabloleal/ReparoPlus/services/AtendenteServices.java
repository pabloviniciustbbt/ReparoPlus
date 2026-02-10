package com.pabloleal.ReparoPlus.services;

import com.pabloleal.ReparoPlus.dto.pessoa.PessoaCreateRequestDTO;
import com.pabloleal.ReparoPlus.dto.pessoa.PessoaResponseDTO;
import com.pabloleal.ReparoPlus.dto.pessoa.PessoaUpdateRequestDTO;
import com.pabloleal.ReparoPlus.exceptions.EntidadeAtivaInativaException;
import com.pabloleal.ReparoPlus.exceptions.EntidadeCadastradaException;
import com.pabloleal.ReparoPlus.models.Atendente;
import com.pabloleal.ReparoPlus.models.Pessoa;
import com.pabloleal.ReparoPlus.repositories.AtendenteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AtendenteServices {

    @Autowired
    private AtendenteRepository atendenteRepository;

    public Atendente cadastrarAtendente(PessoaCreateRequestDTO dados) {

        String cpfFormatado = Pessoa.formatarCpf(dados.cpf());

        if (atendenteRepository.existsByCpf(cpfFormatado)){
            throw new EntidadeCadastradaException("Atendente com CPF " + cpfFormatado + " já cadastrado");
        }

        Atendente atendente = new Atendente(dados);
        atendenteRepository.save(atendente);
        return atendente;
    }

    public Atendente atualizarAtendente(PessoaUpdateRequestDTO dados) {
        Atendente atendente = atendenteRepository.findById(dados.id()).
                orElseThrow(() -> new EntityNotFoundException("Atendente com ID " + dados.id() + " não encontrado"));

        if (dados.cpf() != null){
            String cpfFormatado = Pessoa.formatarCpf(dados.cpf());

            if (atendenteRepository.existsByCpf(cpfFormatado)){
                throw new EntidadeCadastradaException("Atendente com CPF " + cpfFormatado + " já cadastrado");
            }
        }

        atendente.atualizarPessoa(dados);
        return atendente;
    }

    public void deletarAtendente(Long id) {
        Atendente atendente = atendenteRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Atendente com ID " + id + " não encontrado"));

        if (!atendente.getAtivo()){
            throw new EntidadeAtivaInativaException("Atendente com ID " + id + " já está inativo");
        }

        atendente.deletarPessoa();
    }

    public Atendente ativarAtendente(Long id){
        Atendente atendente = atendenteRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Atendente com ID " + id+ " não encontrado"));

        if (atendente.getAtivo()){
            throw new EntidadeAtivaInativaException("Atendente com ID " + id + " já está ativo");
        }

        atendente.ativarPessoa();
        return atendente;
    }

    public Atendente buscarAtendenteID(Long id) {

        Atendente atendente = atendenteRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Atendente com ID " + id + " não encontrado"));

        return atendente;
    }

    public Atendente buscarAtendenteCPF(String cpf) {

        String cpfFormatado = Pessoa.formatarCpf(cpf);

        Atendente atendente = atendenteRepository.findByCpf(cpfFormatado).
                orElseThrow(() -> new EntityNotFoundException("Atendente com CPF " +  cpf + " não encontrado"));

        return atendente;
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
