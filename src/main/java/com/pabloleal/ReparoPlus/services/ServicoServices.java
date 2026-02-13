package com.pabloleal.ReparoPlus.services;

import com.pabloleal.ReparoPlus.dto.servico.ServicoCreateRequestDTO;
import com.pabloleal.ReparoPlus.dto.servico.ServicoResponseDTO;
import com.pabloleal.ReparoPlus.dto.servico.ServicoUpdateRequestDTO;
import com.pabloleal.ReparoPlus.exceptions.EntidadeAtivaInativaException;
import com.pabloleal.ReparoPlus.exceptions.EntidadeCadastradaException;
import com.pabloleal.ReparoPlus.models.Servico;
import com.pabloleal.ReparoPlus.repositories.ServicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServicoServices {

    @Autowired
    private ServicoRepository servicoRepository;

    public Servico cadastrarServico(ServicoCreateRequestDTO servicoCreateRequestDTO) {

        if (servicoRepository.existsByNome(servicoCreateRequestDTO.nome())){
            Servico servicoCadastrado = servicoRepository.findByNome(servicoCreateRequestDTO.nome());
            throw new EntidadeCadastradaException("Servico " + servicoCreateRequestDTO.nome() +" ja cadastrado com o ID " + servicoCadastrado.getId());
        }

        Servico servico = new Servico(servicoCreateRequestDTO);
        servicoRepository.save(servico);

        return servico;
    }

    public Servico atualizarServico(ServicoUpdateRequestDTO servicoUpdateRequestDTO) {

        if (servicoRepository.existsByNome(servicoUpdateRequestDTO.nome())){
            Servico servicoCadastrado = servicoRepository.findByNome(servicoUpdateRequestDTO.nome());
            throw new EntidadeCadastradaException("Servico " + servicoUpdateRequestDTO.nome() + " ja cadastrado com o ID " + servicoCadastrado.getId());
        }

        Servico servico = servicoRepository.findById(servicoUpdateRequestDTO.id()).
                orElseThrow(() -> new EntityNotFoundException("Servico com ID " + servicoUpdateRequestDTO.id() + " não encontrado"));

        servico.atualizarServico(servicoUpdateRequestDTO);
        return servico;
    }

    public void deletarServico(Long id) {
        Servico servico = servicoRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Servico com ID + " + id + " não encontrado"));

        if (!servico.getAtivo()){
            throw new EntidadeAtivaInativaException("Servico com ID " + servico.getId() + " já está inativo");
        }

        servico.deletarServico();
    }

    public Servico ativarServico(Long id) {
        Servico servico = servicoRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Servico com ID " + id + " não encontrada"));

        if (servico.getAtivo()){
            throw new EntidadeAtivaInativaException("Servico com ID " + servico.getId() + " já está ativo");
        }

        servico.ativarServico();
        return servico;
    }

    public Servico buscarServicoId(Long id) {
        Servico servico = servicoRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Servico com ID " + id + " não encontrado"));

        return servico;
    }

    public Page<ServicoResponseDTO> listarServicos(Pageable pageable) {
        return servicoRepository.findAll(pageable)
                .map(ServicoResponseDTO::new);
    }

    public Page<ServicoResponseDTO> listarServicosAtivos(Pageable pageable) {
        return servicoRepository.findAllByAtivoTrue(pageable)
                .map(ServicoResponseDTO::new);
    }

    public Page<ServicoResponseDTO> listarServicosInativos(Pageable pageable) {
        return servicoRepository.findAllByAtivoFalse(pageable)
                .map(ServicoResponseDTO::new);
    }
}
