package com.pabloleal.ReparoPlus.controller;

import com.pabloleal.ReparoPlus.dto.pessoa.PessoaCreateRequestDTO;
import com.pabloleal.ReparoPlus.dto.pessoa.PessoaResponseDTO;
import com.pabloleal.ReparoPlus.dto.pessoa.PessoaUpdateRequestDTO;
import com.pabloleal.ReparoPlus.models.Atendente;
import com.pabloleal.ReparoPlus.services.AtendenteServices;
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
@RequestMapping("/atendente")
public class AtendenteController {

    @Autowired
    private AtendenteServices atendenteServices;

    @PostMapping
    @Transactional
    public ResponseEntity<PessoaResponseDTO> cadastrarAtendente(@RequestBody @Valid PessoaCreateRequestDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        Atendente atendente = atendenteServices.cadastrarAtendente(dados);

        URI uri = uriComponentsBuilder.path("/atendente/{id}").buildAndExpand(atendente.getId()).toUri();

        return ResponseEntity.created(uri).body(new PessoaResponseDTO(atendente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PessoaResponseDTO> atualizarAtendente(@RequestBody @Valid PessoaUpdateRequestDTO dados) {
        Atendente atendente = atendenteServices.atualizarAtendente(dados);
        return ResponseEntity.ok(new PessoaResponseDTO(atendente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarAtendente(@PathVariable Long id) {
        atendenteServices.deletarAtendente(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/ativar/{id}")
    @Transactional
    public ResponseEntity<PessoaResponseDTO> ativarAtendente(@PathVariable Long id) {
        Atendente atendente = atendenteServices.ativarAtendente(id);
        return ResponseEntity.ok(new PessoaResponseDTO(atendente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponseDTO> buscarAtendenteID(@PathVariable Long id) {
        Atendente atendente = atendenteServices.buscarAtendenteID(id);

        return ResponseEntity.ok(new PessoaResponseDTO(atendente));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PessoaResponseDTO> buscarAtendenteCPF(@PathVariable String cpf) {
        Atendente atendente = atendenteServices.buscarAtendenteCPF(cpf);

        return ResponseEntity.ok(new PessoaResponseDTO(atendente));
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<PessoaResponseDTO>> listarAtendentes(Pageable pageable) {
        Page<PessoaResponseDTO> page = atendenteServices.listarAtendentes(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/ativos")
    public ResponseEntity<Page<PessoaResponseDTO>> listarAtendentesAtivos(Pageable pageable) {
        Page<PessoaResponseDTO> page = atendenteServices.listarAtendentesAtivos(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/inativos")
    public ResponseEntity<Page<PessoaResponseDTO>> listarAtendentesInativos(Pageable pageable) {
        Page<PessoaResponseDTO> page = atendenteServices.listarAtendentesInativos(pageable);
        return ResponseEntity.ok(page);
    }
}
