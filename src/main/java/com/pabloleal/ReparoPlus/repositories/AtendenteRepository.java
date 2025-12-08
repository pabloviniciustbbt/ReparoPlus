package com.pabloleal.ReparoPlus.repositories;

import com.pabloleal.ReparoPlus.models.Atendente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AtendenteRepository extends JpaRepository<Atendente, Long> {
    Optional<Atendente> findByCpf(String cpf);

    Page<Atendente> findAllByAtivoTrue(Pageable pageable);

    Page<Atendente> findAllByAtivoFalse(Pageable pageable);
}
