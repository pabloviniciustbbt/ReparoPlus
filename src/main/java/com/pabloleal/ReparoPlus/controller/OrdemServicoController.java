package com.pabloleal.ReparoPlus.controller;

import com.pabloleal.ReparoPlus.dto.*;
import com.pabloleal.ReparoPlus.models.OrdemServico;
import com.pabloleal.ReparoPlus.services.OrdemServicoServices;
import com.pabloleal.ReparoPlus.utils.AvisoContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ordemservico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoServices ordemServicoServices;

    @PostMapping
    public ResponseEntity<OrdemServicoResponseDTO> cadastrarOrdemServico(@RequestBody @Valid OrdemServicoRequestDTO dados, UriComponentsBuilder uriComponentsBuilder){
        OrdemServico ordemServico = ordemServicoServices.cadastrarOrdemServico(dados);

        URI uri = uriComponentsBuilder.path("/ordemservico/{id}").buildAndExpand(ordemServico.getId()).toUri();

        ResponseEntity.BodyBuilder response = ResponseEntity.created(uri);

        if (AvisoContext.temAvisos()){
            String avisos = String.join(" | ", AvisoContext.obterAvisos());
            response.header("X-Avisos", avisos);
        }
        return response.body(new OrdemServicoResponseDTO(ordemServico));
    }

    @PutMapping
    public ResponseEntity atualizarOrdemServico(@RequestBody OrdemServicoUpdateRequestDTO dados){
        OrdemServico ordemServico = ordemServicoServices.atualizarOrdemServico(dados);
        return ResponseEntity.ok(new OrdemServicoResponseDTO(ordemServico));
    }

    @DeleteMapping("{id}")
    public ResponseEntity cancelarOrdemServico(@PathVariable Long id){
        ordemServicoServices.cancelarOrdemServico(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<OrdemServicoResponseDTO> buscarOrdemServicoID(@PathVariable Long id){
        OrdemServico ordemServico = ordemServicoServices.buscarOrdemServicoID(id);
        return ResponseEntity.ok(new OrdemServicoResponseDTO(ordemServico));
    }

    @GetMapping("/todas")
    public ResponseEntity<Page<DadosListagemOrdemServicoDTO>> listarOrdensServico(Pageable pageable){
        Page<DadosListagemOrdemServicoDTO> page = ordemServicoServices.listarTodasOrdensServico(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/abertas")
    public ResponseEntity<Page<DadosListagemOrdemServicoDTO>> listarOrdensServicoAbertas(Pageable pageable){
        Page<DadosListagemOrdemServicoDTO> page = ordemServicoServices.listarOrdensServicoAbertas(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/canceladas")
    public ResponseEntity<Page<DadosListagemOrdemServicoDTO>> listarOrdensServicoCanceladas(Pageable pageable){
        Page<DadosListagemOrdemServicoDTO> page = ordemServicoServices.listarOrdensServicoCanceladas(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/tecnico/{id}")
    public ResponseEntity<Page<DadosListagemOrdemServicoDTO>> listarOrdensServicoPorTecnico(@PathVariable Long id, Pageable pageable){
        Page<DadosListagemOrdemServicoDTO> page = ordemServicoServices.listarOrdensServicoPorTecnico(id, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<Page<DadosListagemOrdemServicoDTO>> listarOrdensServicoPorCliente(@PathVariable Long id, Pageable pageable){
        Page<DadosListagemOrdemServicoDTO> page = ordemServicoServices.listarOrdensServicoPorCliente(id, pageable);
        return  ResponseEntity.ok(page);
    }

    @GetMapping("/historicoStatusOS/{id}")
    public ResponseEntity<List<HistoricoStatusOSResponseDTO>> listarHistoricoStatusOS(@PathVariable Long id){
        List<HistoricoStatusOSResponseDTO> historicoStatusOSResponseDTOS = ordemServicoServices.listarHistoricoStatusOS(id);
        return ResponseEntity.ok(historicoStatusOSResponseDTOS);
    }
}
