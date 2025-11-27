package com.pabloleal.ReparoPlus.controller;

import com.pabloleal.ReparoPlus.dto.OrdemServicoRequestDTO;
import com.pabloleal.ReparoPlus.dto.OrdemServicoResponseDTO;
import com.pabloleal.ReparoPlus.dto.OrdemServicoUpdateRequestDTO;
import com.pabloleal.ReparoPlus.services.OrdemServicoServices;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/id/{id}")
    public ResponseEntity<OrdemServicoResponseDTO> buscarOrdemServicoID(@PathVariable Long id){

        OrdemServicoResponseDTO ordemServicoDTO = ordemServicoServices.buscarOrdemServicoID(id);

        if (ordemServicoDTO != null){
            return ResponseEntity.ok(ordemServicoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
