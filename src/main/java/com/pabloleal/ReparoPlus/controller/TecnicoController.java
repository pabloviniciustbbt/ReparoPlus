package com.pabloleal.ReparoPlus.controller;

import com.pabloleal.ReparoPlus.dto.pessoa.PessoaCreateRequestDTO;
import com.pabloleal.ReparoPlus.dto.pessoa.PessoaResponseDTO;
import com.pabloleal.ReparoPlus.dto.pessoa.PessoaUpdateRequestDTO;
import com.pabloleal.ReparoPlus.models.Tecnico;
import com.pabloleal.ReparoPlus.services.TecnicoServices;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/tecnico")
public class TecnicoController {

    @Autowired
    private TecnicoServices tecnicoServices;

    @PostMapping
    @Transactional
    public ResponseEntity<PessoaResponseDTO> cadastrarTecnico(@RequestBody @Valid PessoaCreateRequestDTO dados, UriComponentsBuilder uriComponentsBuilder){
        Tecnico tecnico = tecnicoServices.cadastrarTecnico(dados);

        URI uri = uriComponentsBuilder.path("/tecnico/{id}").buildAndExpand(tecnico.getId()).toUri();
        return ResponseEntity.created(uri).body(new PessoaResponseDTO(tecnico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PessoaResponseDTO> atualizarTecnico(@RequestBody @Valid PessoaUpdateRequestDTO dados){
        Tecnico tecnico = tecnicoServices.atualizarTecnico(dados);
        return ResponseEntity.ok(new PessoaResponseDTO(tecnico));
    }

    @DeleteMapping ("/{id}")
    @Transactional
    public ResponseEntity deletarTecnico(@PathVariable Long id){
        tecnicoServices.deletarTecnico(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping ("/ativar/{id}")
    @Transactional
    public ResponseEntity<PessoaResponseDTO> ativarTecnico(@PathVariable Long id){
        Tecnico tecnico = tecnicoServices.ativarTecnico(id);
        return ResponseEntity.ok(new PessoaResponseDTO(tecnico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponseDTO> buscarTecnicoID(@PathVariable Long id){
        Tecnico tecnico = tecnicoServices.buscarTecnicoID(id);

        return ResponseEntity.ok(new PessoaResponseDTO(tecnico));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PessoaResponseDTO> buscarTecnicoCPF(@PathVariable String cpf){
        Tecnico tecnico = tecnicoServices.buscarTecnicoCPF(cpf);

        return ResponseEntity.ok(new PessoaResponseDTO(tecnico));
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
