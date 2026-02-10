package com.pabloleal.ReparoPlus.controller;

import com.pabloleal.ReparoPlus.dto.produto.ProdutoCreateRequestDTO;
import com.pabloleal.ReparoPlus.dto.produto.ProdutoResponseDTO;
import com.pabloleal.ReparoPlus.dto.produto.ProdutoResumoResponseDTO;
import com.pabloleal.ReparoPlus.dto.produto.ProdutoUpdateRequestDTO;
import com.pabloleal.ReparoPlus.models.Produto;
import com.pabloleal.ReparoPlus.services.ProdutoServices;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoServices produtoServices;

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoResponseDTO> cadastrarProduto(@RequestBody @Valid ProdutoCreateRequestDTO produtoCreateRequestDTO, UriComponentsBuilder uriComponentsBuilder) {
        Produto produto = produtoServices.cadastrarProduto(produtoCreateRequestDTO);

        URI uri = uriComponentsBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();

        ResponseEntity.BodyBuilder response = ResponseEntity.created(uri);

        return response.body(new ProdutoResponseDTO(produto));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(@RequestBody @Valid ProdutoUpdateRequestDTO produtoUpdateRequestDTO) {
        Produto produto = produtoServices.atualizarProduto(produtoUpdateRequestDTO);
        return ResponseEntity.ok(new ProdutoResponseDTO(produto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarProduto(@PathVariable Long id) {
        produtoServices.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/ativar/{id}")
    @Transactional
    public ResponseEntity<ProdutoResumoResponseDTO> ativarProduto(@PathVariable Long id) {
        Produto produto = produtoServices.ativarProduto(id);
        return ResponseEntity.ok(new ProdutoResumoResponseDTO(produto));
    }
}
