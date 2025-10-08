package com.pabloleal.ReparoPlus.controller;

import com.pabloleal.ReparoPlus.dto.AtendenteCreateRequestDTO;
import com.pabloleal.ReparoPlus.services.AtendenteServices;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atendente")
public class AtendenteController {

    @Autowired
    private AtendenteServices atendenteServices;

    @PostMapping
    @Transactional
    public ResponseEntity<AtendenteCreateRequestDTO> cadastrarAtendente(@RequestBody @Valid AtendenteCreateRequestDTO dados){
        atendenteServices.cadastrarAtendente(dados);
        return ResponseEntity.ok(dados);
    }
}
