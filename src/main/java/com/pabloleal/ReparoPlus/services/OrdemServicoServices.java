package com.pabloleal.ReparoPlus.services;

import com.pabloleal.ReparoPlus.dto.*;
import com.pabloleal.ReparoPlus.exceptions.OrdemServicoException;
import com.pabloleal.ReparoPlus.models.*;
import com.pabloleal.ReparoPlus.repositories.AtendenteRepository;
import com.pabloleal.ReparoPlus.repositories.ClienteRepository;
import com.pabloleal.ReparoPlus.repositories.OrdemServicoRepository;
import com.pabloleal.ReparoPlus.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;


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


    public OrdemServicoResponseDTO cadastrarOrdemServico(OrdemServicoRequestDTO dados){

            Cliente cliente = clienteRepository.findByCpf(dados.cpfCliente())
                    .orElseThrow(() -> new OrdemServicoException("Cliente com CPF " + dados.cpfCliente() + " não cadastrado" +
                            "\nPor favor, cadastre o cliente antes de criar a ordem de serviço"
            ));
            if (!cliente.getAtivo()){
                throw new OrdemServicoException("Cliente com CPF " + dados.cpfCliente() + " está inativo");
            }

            Atendente atendente = atendenteRepository.findById(dados.atendenteId())
                    .orElseThrow(() -> new OrdemServicoException("Atendente com ID " + dados.atendenteId() + " não encontrado"));

            if (!atendente.getAtivo()){
                throw new OrdemServicoException("Atendente com ID " + dados.atendenteId() + " está inativo");
            }

            Tecnico tecnico = tecnicoRepository.findById(dados.tecnicoId())
                    .orElseThrow(() -> new OrdemServicoException("Tecnico com ID " + dados.tecnicoId() + " não encontrado"));
            if (!tecnico.getAtivo()){
                throw new OrdemServicoException("Tecnico com ID " + dados.tecnicoId() + " está inativo");
            }

            OrdemServico ordemServico = new OrdemServico(cliente, dados.equipamento(), atendente, tecnico, dados.statusOS(), dados.observacoesTecnicas(), dados.observacoesOrdemServico());

            ordemServico = ordemServicoRepository.save(ordemServico);

            return new OrdemServicoResponseDTO(
                    ordemServico.getId(),
                    new ClienteResponseDTO(
                            ordemServico.getCliente().getId(),
                            ordemServico.getCliente().getCpf(),
                            ordemServico.getCliente().getNome(),
                            ordemServico.getCliente().getEmail(),
                            ordemServico.getCliente().getTelefone(),
                            ordemServico.getCliente().getEndereco()
                    ),
                    ordemServico.getEquipamento(),
                    //Atendente
                    new PessoaResumoResponseDTO(
                            ordemServico.getAtendente().getId(),
                            ordemServico.getAtendente().getNome()
                    ),
                    //Tecnico
                    new PessoaResumoResponseDTO(
                            ordemServico.getTecnico().getId(),
                            ordemServico.getTecnico().getNome()
                    ),
                    ordemServico.getStatusOS(),
                    ordemServico.getObservacoesTecnicas(),
                    ordemServico.getObservacoesOrdemServico(),
                    ordemServico.getDataHoraAbertura());
    }

    public void atualizarOrdemServico(OrdemServicoUpdateRequestDTO dados) {

        OrdemServico ordemServico = ordemServicoRepository.findById(dados.id()).
                orElseThrow(() -> new OrdemServicoException("Ordem de Serviço não encontrada"));

        if (dados.cpfCliente() != null){
            Cliente cliente = clienteRepository.findByCpf(dados.cpfCliente()).
                    orElseThrow(() -> new OrdemServicoException("Cliente com CPF " + dados.cpfCliente() + " não encontrado"));
            if (!cliente.getAtivo()){
                throw new OrdemServicoException("Cliente com CPF " + dados.cpfCliente() + " está inativo");
            }
            ordemServico.setCliente(cliente);
        }
        if (dados.atendenteId() != null) {
            Atendente atendente = atendenteRepository.findById(dados.atendenteId()).
                    orElseThrow(() -> new OrdemServicoException("Atendente com ID " + dados.atendenteId() + " não encontrado"));
            if (!atendente.getAtivo()){
                throw new OrdemServicoException("Atendente com ID " + dados.atendenteId() + " está inativo");
            }
            ordemServico.setAtendente(atendente);
        }
        if (dados.tecnicoId() != null) {
            Tecnico tecnico = tecnicoRepository.findById(dados.tecnicoId()).
                    orElseThrow(() -> new OrdemServicoException("Tecnico com ID " + dados.tecnicoId() + " não encontrado"));
            if (!tecnico.getAtivo()){
                throw new OrdemServicoException("Tecnico com ID " + dados.tecnicoId() + " está inativo");
            }
            ordemServico.setTecnico(tecnico);
        }

        ordemServico.atualizarOrdemServico(dados);

        ordemServicoRepository.save(ordemServico);
    }

    public void cancelarOrdemServico(Long id) {
        OrdemServico ordemServico = ordemServicoRepository.getReferenceById(id);
        ordemServico.cancelarOrdemServico();
    }

    public OrdemServicoResponseDTO buscarOrdemServicoID(Long id) {

        Optional<OrdemServico> ordemServicoDTO = ordemServicoRepository.findById(id);

        if (ordemServicoDTO.isPresent()){
            OrdemServico os = ordemServicoDTO.get();
            return new OrdemServicoResponseDTO(
                    os.getId(),
                    new ClienteResponseDTO(
                            os.getCliente().getId(),
                            os.getCliente().getCpf(),
                            os.getCliente().getNome(),
                            os.getCliente().getEmail(),
                            os.getCliente().getTelefone(),
                            os.getCliente().getEndereco()
                            ),
                    os.getEquipamento(),
                    //Atendente
                    new PessoaResumoResponseDTO(
                            os.getAtendente().getId(),
                            os.getAtendente().getNome()
                    ),
                    //Tecnico
                    new PessoaResumoResponseDTO(
                            os.getTecnico().getId(),
                            os.getTecnico().getNome()
                    ),
                    os.getStatusOS(),
                    os.getObservacoesTecnicas(),
                    os.getObservacoesOrdemServico(),
                    os.getDataHoraAbertura());
        }

        return null;
    }

    public Page<DadosListagemOrdemServicoDTO> listarTodasOrdensServico(Pageable pageable) {
        return ordemServicoRepository.findAll(pageable)
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
}
