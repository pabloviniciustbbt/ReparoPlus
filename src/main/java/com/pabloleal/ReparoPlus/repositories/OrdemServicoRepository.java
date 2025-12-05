package com.pabloleal.ReparoPlus.repositories;

import com.pabloleal.ReparoPlus.models.OrdemServico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico,Long> {
    Page<OrdemServico> findAllByTecnicoId(Long id, Pageable pageable);

    Page<OrdemServico> findAllByClienteId(Long id, Pageable pageable);
}
