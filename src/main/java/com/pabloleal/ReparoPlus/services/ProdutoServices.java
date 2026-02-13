package com.pabloleal.ReparoPlus.services;

import com.pabloleal.ReparoPlus.dto.produto.ProdutoCreateRequestDTO;
import com.pabloleal.ReparoPlus.dto.produto.ProdutoResponseDTO;
import com.pabloleal.ReparoPlus.dto.produto.ProdutoUpdateRequestDTO;
import com.pabloleal.ReparoPlus.exceptions.EntidadeAtivaInativaException;
import com.pabloleal.ReparoPlus.exceptions.EntidadeCadastradaException;
import com.pabloleal.ReparoPlus.models.Produto;
import com.pabloleal.ReparoPlus.repositories.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServices {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto cadastrarProduto(ProdutoCreateRequestDTO produtoCreateRequestDTO) {

        if (produtoRepository.existsByCodigoEan(produtoCreateRequestDTO.codigoEan())) {
            Produto produtoCadastrado = produtoRepository.findByCodigoEan(produtoCreateRequestDTO.codigoEan());
            throw new EntidadeCadastradaException("Produto com Código EAN" + produtoCreateRequestDTO.codigoEan() +
                    " já cadastrado com ID " + produtoCadastrado.getId());
        }

        Produto produto = new Produto(produtoCreateRequestDTO);
        produtoRepository.save(produto);
        return produto;
    }

    public Produto atualizarProduto(ProdutoUpdateRequestDTO produtoUpdateRequestDTO) {

        Produto produto = produtoRepository.findById(produtoUpdateRequestDTO.id()).
                orElseThrow(() -> new EntityNotFoundException("Produto com ID " + produtoUpdateRequestDTO.id() + " não encontrado"));

        if (produtoRepository.existsByCodigoEan(produtoUpdateRequestDTO.codigoEan())) {
            Produto produtoCadastrado = produtoRepository.findByCodigoEan(produtoUpdateRequestDTO.codigoEan());
            throw new EntidadeCadastradaException("Produto com EAN " + produtoUpdateRequestDTO.codigoEan() + " já cadastrado com ID " + produtoCadastrado.getId());
        }

        produto.atualizaProduto(produtoUpdateRequestDTO);

        return produto;
    }

    public void deletarProduto(Long id) {
        Produto produto = produtoRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Produto com ID " + id + " não encontrado"));

        if (!produto.isAtivo()) {
            throw new EntidadeAtivaInativaException("Produto com ID " + id + " já está inativo");
        }

        produto.deletarProduto();
    }

    public Produto ativarProduto(Long id) {
        Produto produto = produtoRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Produto com ID " + id + " não cadastrado"));

        if (produto.isAtivo()) {
            throw new EntidadeAtivaInativaException("Produto com ID " + id + " já está ativo");
        }

        produto.ativarProduto();
        return produto;
    }

    public Page<ProdutoResponseDTO> listarProdutos(Pageable pageable) {
        return produtoRepository.findAll(pageable).
                map(ProdutoResponseDTO::new);
    }

    public Page<ProdutoResponseDTO> listarProdutosAtivos(Pageable pageable) {
        return produtoRepository.findAllByAtivoTrue(pageable)
                .map(ProdutoResponseDTO::new);
    }

    public Page<ProdutoResponseDTO> listarProdutosInativos(Pageable pageable) {
        return produtoRepository.findAllByAtivoFalse(pageable)
                .map(ProdutoResponseDTO::new);
    }
}
