package com.pabloleal.ReparoPlus.controller;

import com.pabloleal.ReparoPlus.dto.servico.ServicoCreateRequestDTO;
import com.pabloleal.ReparoPlus.dto.servico.ServicoResponseDTO;
import com.pabloleal.ReparoPlus.dto.servico.ServicoUpdateRequestDTO;
import com.pabloleal.ReparoPlus.models.Servico;
import com.pabloleal.ReparoPlus.services.ServicoServices;
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
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    private ServicoServices servicoServices;

    @PostMapping
    @Transactional
    public ResponseEntity<ServicoResponseDTO> cadastrarServico(@RequestBody @Valid ServicoCreateRequestDTO servicoCreateRequestDTO, UriComponentsBuilder uriComponentsBuilder) {
        Servico servico = servicoServices.cadastrarServico(servicoCreateRequestDTO);

        URI uri = uriComponentsBuilder.path("/servico/{id}").buildAndExpand(servico.getId()).toUri();

        return ResponseEntity.created(uri).body(new ServicoResponseDTO(servico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ServicoResponseDTO> atualizarServico(@RequestBody @Valid ServicoUpdateRequestDTO servicoUpdateRequestDTO) {
        Servico servico = servicoServices.atualizarServico(servicoUpdateRequestDTO);
        return ResponseEntity.ok(new ServicoResponseDTO(servico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarServico(@PathVariable Long id) {
        servicoServices.deletarServico(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<ServicoResponseDTO> ativarServico(@PathVariable Long id) {
        Servico servico = servicoServices.ativarServico(id);
        return ResponseEntity.ok(new ServicoResponseDTO(servico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> buscarServicoId(@PathVariable Long id) {
        Servico servico = servicoServices.buscarServicoId(id);
        return ResponseEntity.ok(new ServicoResponseDTO(servico));
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<ServicoResponseDTO>> listarServicos(Pageable pageable) {
        Page<ServicoResponseDTO> page = servicoServices.listarServicos(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/ativos")
    public ResponseEntity<Page<ServicoResponseDTO>> listarServicosAtivos(Pageable pageable) {
        Page<ServicoResponseDTO> page = servicoServices.listarServicosAtivos(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/inativos")
    public ResponseEntity<Page<ServicoResponseDTO>> listarServicosInativos(Pageable pageable) {
        Page<ServicoResponseDTO> page = servicoServices.listarServicosInativos(pageable);
        return ResponseEntity.ok(page);
    }
}
