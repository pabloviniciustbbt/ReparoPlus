package com.pabloleal.ReparoPlus.controller;

import com.pabloleal.ReparoPlus.dto.PessoaCreateRequestDTO;
import com.pabloleal.ReparoPlus.dto.PessoaResponseDTO;
import com.pabloleal.ReparoPlus.dto.PessoaUpdateRequestDTO;
import com.pabloleal.ReparoPlus.services.AtendenteServices;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atendente")
public class AtendenteController {

    @Autowired
    private AtendenteServices atendenteServices;

    @PostMapping
    @Transactional
    public ResponseEntity<PessoaCreateRequestDTO> cadastrarAtendente(@RequestBody @Valid PessoaCreateRequestDTO dados){
        atendenteServices.cadastrarAtendente(dados);
        return ResponseEntity.ok(dados);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PessoaUpdateRequestDTO> atualizarAtendente(@RequestBody @Valid PessoaUpdateRequestDTO dados){
        atendenteServices.atualizarAtendente(dados);
        return ResponseEntity.ok(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarAtendente(@PathVariable Long id){
        atendenteServices.deletarAtendente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PessoaResponseDTO> buscarAtendenteID(@PathVariable Long id){
        PessoaResponseDTO atendenteDTO = atendenteServices.buscarAtendenteID(id);

        if (atendenteDTO != null){
            return ResponseEntity.ok(atendenteDTO);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PessoaResponseDTO> buscarAtendenteCPF(@PathVariable String cpf){
        PessoaResponseDTO atendenteDTO = atendenteServices.buscarAtendenteCPF(cpf);

        if (atendenteDTO != null){
            return ResponseEntity.ok(atendenteDTO);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
