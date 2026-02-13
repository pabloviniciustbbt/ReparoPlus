package com.pabloleal.ReparoPlus.repositories;

import com.pabloleal.ReparoPlus.models.Servico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    boolean existsByNome(String nome);
    Servico findByNome(String nome);

    Page<Servico> findAllByAtivoTrue(Pageable pageable);

    Page<Servico> findAllByAtivoFalse(Pageable pageable);
}
