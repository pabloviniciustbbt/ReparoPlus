package com.pabloleal.ReparoPlus.services;

import com.pabloleal.ReparoPlus.dto.OrdemServicoRequestDTO;
import com.pabloleal.ReparoPlus.dto.OrdemServicoResponseDTO;
import com.pabloleal.ReparoPlus.exceptions.OrdemServicoException;
import com.pabloleal.ReparoPlus.models.Atendente;
import com.pabloleal.ReparoPlus.models.Cliente;
import com.pabloleal.ReparoPlus.models.OrdemServico;
import com.pabloleal.ReparoPlus.models.Tecnico;
import com.pabloleal.ReparoPlus.repositories.AtendenteRepository;
import com.pabloleal.ReparoPlus.repositories.ClienteRepository;
import com.pabloleal.ReparoPlus.repositories.OrdemServicoRepository;
import com.pabloleal.ReparoPlus.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
                    ordemServico.getCliente(),
                    ordemServico.getEquipamento(),
                    ordemServico.getAtendente(),
                    ordemServico.getTecnico(),
                    ordemServico.getStatusOS(),
                    ordemServico.getObservacoesTecnicas(),
                    ordemServico.getObservacoesOrdemServico(),
                    ordemServico.getDataHoraAbertura());
    }
}
