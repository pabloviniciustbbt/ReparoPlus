package com.pabloleal.ReparoPlus.controller;

import com.pabloleal.ReparoPlus.dto.ClienteCreateRequestDTO;
import com.pabloleal.ReparoPlus.dto.ClienteUpdateRequestDTO;
import com.pabloleal.ReparoPlus.services.ClienteServices;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteServices clienteServices;

    @PostMapping
    @Transactional
    public ResponseEntity<ClienteCreateRequestDTO> cadastrarCliente(@RequestBody @Valid ClienteCreateRequestDTO dados){
        clienteServices.cadastrarCliente(dados);
        return ResponseEntity.ok(dados);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ClienteUpdateRequestDTO> atualizarCliente(@RequestBody @Valid ClienteUpdateRequestDTO dados){
        clienteServices.atualizarCliente(dados);
        return ResponseEntity.ok(dados);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deletarCliente(@PathVariable Long id){
        clienteServices.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }




}
