package com.pabloleal.ReparoPlus.repositories;

import com.pabloleal.ReparoPlus.models.Tecnico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {
    Optional<Tecnico> findByCpf(String cpf);

    Page<Tecnico> findAllByAtivoTrue(Pageable pageable);

    Page<Tecnico> findAllByAtivoFalse(Pageable pageable);

    boolean existsByCpf(String cpfFormatado);

    Tecnico getReferenceByCpf(String cpfFormatado);
}
