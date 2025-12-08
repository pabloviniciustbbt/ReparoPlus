package com.pabloleal.ReparoPlus.controller;

import com.pabloleal.ReparoPlus.dto.PessoaCreateRequestDTO;
import com.pabloleal.ReparoPlus.dto.PessoaResponseDTO;
import com.pabloleal.ReparoPlus.dto.PessoaUpdateRequestDTO;
import com.pabloleal.ReparoPlus.services.TecnicoServices;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/id/{id}")
    public ResponseEntity<PessoaResponseDTO> buscarTecnicoID(@PathVariable Long id){
        PessoaResponseDTO tecnicoDTO = tecnicoServices.buscarTecnicoID(id);

        if (tecnicoDTO != null){
            return ResponseEntity.ok(tecnicoDTO);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PessoaResponseDTO> buscarTecnicoCPF(@PathVariable String cpf){
        PessoaResponseDTO tecnicoDTO = tecnicoServices.buscarTecnicoCPF(cpf);

        if (tecnicoDTO != null){
            return ResponseEntity.ok(tecnicoDTO);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<PessoaResponseDTO>> listarTecnicos(Pageable pageable){
        Page<PessoaResponseDTO> page = tecnicoServices.listarTecnicos(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/ativos")
    public ResponseEntity<Page<PessoaResponseDTO>> listarTecnicosAtivos(Pageable pageable){
        Page<PessoaResponseDTO> page = tecnicoServices.listarTecnicosAtivos(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/inativos")
    public ResponseEntity<Page<PessoaResponseDTO>> listarTecnicosInativos(Pageable pageable){
        Page<PessoaResponseDTO> page = tecnicoServices.listarTecnicosInativos(pageable);
        return ResponseEntity.ok(page);
    }

}
