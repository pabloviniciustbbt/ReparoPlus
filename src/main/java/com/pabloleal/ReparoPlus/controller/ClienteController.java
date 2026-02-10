package com.pabloleal.ReparoPlus.controller;

import com.pabloleal.ReparoPlus.dto.cliente.ClienteCreateRequestDTO;
import com.pabloleal.ReparoPlus.dto.cliente.ClienteResponseDTO;
import com.pabloleal.ReparoPlus.dto.cliente.ClienteUpdateRequestDTO;
import com.pabloleal.ReparoPlus.models.Cliente;
import com.pabloleal.ReparoPlus.services.ClienteServices;
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
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteServices clienteServices;

    @PostMapping
    @Transactional
    public ResponseEntity<ClienteResponseDTO> cadastrarCliente(@RequestBody @Valid ClienteCreateRequestDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        Cliente cliente = clienteServices.cadastrarCliente(dados);

        URI uri = uriComponentsBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(new ClienteResponseDTO(cliente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(@RequestBody @Valid ClienteUpdateRequestDTO dados) {
        Cliente cliente = clienteServices.atualizarCliente(dados);
        return ResponseEntity.ok(new ClienteResponseDTO(cliente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarCliente(@PathVariable Long id) {
        clienteServices.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/ativar/{id}")
    @Transactional
    public ResponseEntity<ClienteResponseDTO> ativarCliente(@PathVariable Long id) {
        Cliente cliente = clienteServices.ativarCliente(id);
        return ResponseEntity.ok(new ClienteResponseDTO(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarClienteID(@PathVariable Long id) {
        Cliente cliente = clienteServices.buscarClienteID(id);
        return ResponseEntity.ok(new ClienteResponseDTO(cliente));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteResponseDTO> buscarClienteCPF(@PathVariable String cpf) {
        Cliente cliente = clienteServices.buscarClienteCPF(cpf);
        return ResponseEntity.ok(new ClienteResponseDTO(cliente));
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<ClienteResponseDTO>> listarClientes(Pageable pageable) {
        Page<ClienteResponseDTO> page = clienteServices.listarClientes(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/ativos")
    public ResponseEntity<Page<ClienteResponseDTO>> listarClientesAtivos(Pageable pageable) {
        Page<ClienteResponseDTO> page = clienteServices.listarClientesAtivos(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/inativos")
    public ResponseEntity<Page<ClienteResponseDTO>> listarClientesInativos(Pageable pageable) {
        Page<ClienteResponseDTO> page = clienteServices.listarClientesInativos(pageable);
        return ResponseEntity.ok(page);
    }
}
