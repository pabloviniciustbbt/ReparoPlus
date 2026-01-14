package com.pabloleal.ReparoPlus.services;

import com.pabloleal.ReparoPlus.dto.*;
import com.pabloleal.ReparoPlus.exceptions.EntidadeCadastradaException;
import com.pabloleal.ReparoPlus.exceptions.EntidadeInativaException;
import com.pabloleal.ReparoPlus.models.*;
import com.pabloleal.ReparoPlus.repositories.*;
import com.pabloleal.ReparoPlus.utils.AvisoContext;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrdemServicoServices {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    @Autowired
    private AtendenteRepository atendenteRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private HistoricoStatusOSRepository historicoStatusOSRepository;


    @Transactional
    public OrdemServico cadastrarOrdemServico(OrdemServicoRequestDTO dados){

            Cliente cliente = clienteRepository.findByCpf(dados.cpfCliente())
                    .orElseThrow(() -> new EntityNotFoundException("Cliente com CPF " + dados.cpfCliente() + " não cadastrado" +
                            "\nPor favor, cadastre o cliente antes de criar a ordem de serviço"
            ));
            if (!cliente.getAtivo()){
                throw new EntidadeInativaException("Cliente com CPF " + dados.cpfCliente() + " está inativo");
            }

            Atendente atendente = atendenteRepository.findById(dados.atendenteId())
                    .orElseThrow(() -> new EntityNotFoundException("Atendente com ID " + dados.atendenteId() + " não encontrado"));

            if (!atendente.getAtivo()){
                throw new EntidadeInativaException("Atendente com ID " + dados.atendenteId() + " está inativo");
            }

            Tecnico tecnico = tecnicoRepository.findById(dados.tecnicoId())
                    .orElseThrow(() -> new EntityNotFoundException("Tecnico com ID " + dados.tecnicoId() + " não encontrado"));
            if (!tecnico.getAtivo()){
                throw new EntidadeInativaException("Tecnico com ID " + dados.tecnicoId() + " está inativo");
            }

            List<OrdemServico> outrasOrdemServico = ordemServicoRepository.findAllByClienteId(cliente.getId());

            if (!outrasOrdemServico.isEmpty()){
                String aviso = String.format("O Cliente " + cliente.getNome() +" possui " + outrasOrdemServico.size() + " OS`s em aberto");
                AvisoContext.adicionarAviso(aviso);

                for (int i = 0; i < outrasOrdemServico.size(); i++) {
                    OrdemServico os = outrasOrdemServico.get(i);

                    if (os.getEquipamento().getNumeroSerie().equals(dados.equipamento().getNumeroSerie()) && os.isAtivo()){
                        throw new EntidadeCadastradaException("Equipamento com o serial " + dados.equipamento().getNumeroSerie() + " já está cadastrado na OS " + os.getId());
                    }
                }
            }

            OrdemServico ordemServico = new OrdemServico(cliente, dados.equipamento(), atendente, tecnico, dados.statusOS(), dados.observacoesTecnicas(), dados.observacoesOrdemServico());
            ordemServico = ordemServicoRepository.save(ordemServico);

            HistoricoStatusOS historicoStatusOS = new HistoricoStatusOS(ordemServico, atendente, dados.statusOS());
            historicoStatusOSRepository.save(historicoStatusOS);

            return ordemServico;
    }

    @Transactional
    public OrdemServico atualizarOrdemServico(OrdemServicoUpdateRequestDTO dados) {

        OrdemServico ordemServico = ordemServicoRepository.findById(dados.id()).
                orElseThrow(() -> new EntityNotFoundException("Ordem de Serviço não encontrada"));

        StatusOS statusAnterior = ordemServico.getStatusOS();

        if (!ordemServico.isAtivo()){
            throw new EntidadeInativaException("Não é possível atualizar uma ordem de serviço cancelada");
        }

        if (dados.cpfCliente() != null){
            Cliente cliente = clienteRepository.findByCpf(dados.cpfCliente()).
                    orElseThrow(() -> new EntityNotFoundException("Cliente com CPF " + dados.cpfCliente() + " não encontrado"));
            if (!cliente.getAtivo()){
                throw new EntidadeInativaException("Cliente com CPF " + dados.cpfCliente() + " está inativo");
            }
            ordemServico.setCliente(cliente);
        }
        if (dados.atendenteId() != null) {
            Atendente atendente = atendenteRepository.findById(dados.atendenteId()).
                    orElseThrow(() -> new EntityNotFoundException("Atendente com ID " + dados.atendenteId() + " não encontrado"));
            if (!atendente.getAtivo()){
                throw new EntidadeInativaException("Atendente com ID " + dados.atendenteId() + " está inativo");
            }
            ordemServico.setAtendente(atendente);
        }
        if (dados.tecnicoId() != null) {
            Tecnico tecnico = tecnicoRepository.findById(dados.tecnicoId()).
                    orElseThrow(() -> new EntityNotFoundException("Tecnico com ID " + dados.tecnicoId() + " não encontrado"));
            if (!tecnico.getAtivo()){
                throw new EntidadeInativaException("Tecnico com ID " + dados.tecnicoId() + " está inativo");
            }
            ordemServico.setTecnico(tecnico);
        }

        ordemServico.atualizarOrdemServico(dados);
        ordemServicoRepository.save(ordemServico);

        if (dados.statusOS() != 0 && statusAnterior != StatusOS.fromId(dados.statusOS())){
            HistoricoStatusOS historicoStatusOS = new HistoricoStatusOS(ordemServico, ordemServico.getAtendente(), ordemServico.getStatusOS().getId());
            historicoStatusOSRepository.save(historicoStatusOS);
        }

        return ordemServico;
    }

    @Transactional
    public void cancelarOrdemServico(Long id) {
        OrdemServico ordemServico = ordemServicoRepository.getReferenceById(id);
        ordemServico.cancelarOrdemServico();
    }

    public OrdemServico buscarOrdemServicoID(Long id) {
        OrdemServico ordemServico = ordemServicoRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Ordem de Serviço não encontrada"));
        return ordemServico;
    }

    public Page<DadosListagemOrdemServicoDTO> listarTodasOrdensServico(Pageable pageable) {
        return ordemServicoRepository.findAll(pageable)
                .map(DadosListagemOrdemServicoDTO::new);
    }

    public Page<DadosListagemOrdemServicoDTO> listarOrdensServicoAbertas(Pageable pageable) {
        return ordemServicoRepository.findAllByAtivoTrue(pageable)
                .map(DadosListagemOrdemServicoDTO::new);
    }

    public Page<DadosListagemOrdemServicoDTO> listarOrdensServicoCanceladas(Pageable pageable) {
        return ordemServicoRepository.findAllByAtivoFalse(pageable)
                .map(DadosListagemOrdemServicoDTO::new);
    }

    public Page<DadosListagemOrdemServicoDTO> listarOrdensServicoPorTecnico(Long id, Pageable pageable) {
        return ordemServicoRepository.findAllByTecnicoId(id, pageable)
                .map(DadosListagemOrdemServicoDTO::new);
    }

    public Page<DadosListagemOrdemServicoDTO> listarOrdensServicoPorCliente(Long id, Pageable pageable) {
        return ordemServicoRepository.findAllByClienteId(id,pageable)
                .map(DadosListagemOrdemServicoDTO::new);
    }

    public List<HistoricoStatusOSResponseDTO> listarHistoricoStatusOS(Long id) {
        return historicoStatusOSRepository.findByOrdemServicoIdOrderByDataHoraAlteracaoDesc(id).stream()
                .map(HistoricoStatusOSResponseDTO::new)
                .collect(Collectors.toList());

    }
}
