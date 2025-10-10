package com.pabloleal.ReparoPlus.controller;

import com.pabloleal.ReparoPlus.dto.PessoaCreateRequestDTO;
import com.pabloleal.ReparoPlus.dto.PessoaUpdateRequestDTO;
import com.pabloleal.ReparoPlus.services.TecnicoServices;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tecnico")
public class TecnicoController {

    @Autowired
    private TecnicoServices tecnicoServices;

    @PostMapping
    @Transactional
    public ResponseEntity<PessoaCreateRequestDTO> cadastrarTecnico(@RequestBody @Valid PessoaCreateRequestDTO dados){
        tecnicoServices.cadastrarTecnico(dados);
        return ResponseEntity.ok(dados);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PessoaUpdateRequestDTO> atualizarTecnico(@RequestBody @Valid PessoaUpdateRequestDTO dados){
        tecnicoServices.atualizarTecnico(dados);
        return ResponseEntity.ok(dados);
    }

    @DeleteMapping ("/{id}")
    @Transactional
    public ResponseEntity deletarTecnico(@PathVariable Long id){
        tecnicoServices.deletarTecnico(id);
        return ResponseEntity.noContent().build();
    }

}
