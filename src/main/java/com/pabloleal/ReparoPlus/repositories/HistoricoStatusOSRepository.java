package com.pabloleal.ReparoPlus.repositories;

import com.pabloleal.ReparoPlus.models.HistoricoStatusOS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HistoricoStatusOSRepository extends JpaRepository<HistoricoStatusOS, Long> {
    List<HistoricoStatusOS> findByOrdemServicoIdOrderByDataHoraAlteracaoDesc(Long id);
}
