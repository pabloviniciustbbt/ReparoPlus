package com.pabloleal.ReparoPlus.controller;

import com.pabloleal.ReparoPlus.dto.DadosListagemOrdemServicoDTO;
import com.pabloleal.ReparoPlus.dto.OrdemServicoRequestDTO;
import com.pabloleal.ReparoPlus.dto.OrdemServicoResponseDTO;
import com.pabloleal.ReparoPlus.dto.OrdemServicoUpdateRequestDTO;
import com.pabloleal.ReparoPlus.services.OrdemServicoServices;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ordemservico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoServices ordemServicoServices;

    @PostMapping
    @Transactional
    public ResponseEntity<OrdemServicoRequestDTO> cadastrarOrdemServico(@RequestBody @Valid OrdemServicoRequestDTO dados){

        ordemServicoServices.cadastrarOrdemServico(dados);
        return ResponseEntity.ok(dados);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<OrdemServicoUpdateRequestDTO> atualizarOrdemServico(@RequestBody OrdemServicoUpdateRequestDTO dados){

        ordemServicoServices.atualizarOrdemServico(dados);
        return ResponseEntity.ok(dados);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity cancelarOrdemServico(@PathVariable Long id){
        ordemServicoServices.cancelarOrdemServico(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<OrdemServicoResponseDTO> buscarOrdemServicoID(@PathVariable Long id){

        OrdemServicoResponseDTO ordemServicoDTO = ordemServicoServices.buscarOrdemServicoID(id);

        if (ordemServicoDTO != null){
            return ResponseEntity.ok(ordemServicoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
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
}
