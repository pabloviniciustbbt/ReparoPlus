package com.pabloleal.ReparoPlus.repositories;

import com.pabloleal.ReparoPlus.models.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    boolean existsByCodigoEan(String codigoEan);

    Produto findByCodigoEan(String codigoEan);

    Page<Produto> findAllByAtivoTrue(Pageable pageable);

    Page<Produto> findAllByAtivoFalse(Pageable pageable);
}
