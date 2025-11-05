package com.pabloleal.ReparoPlus.repositories;

import com.pabloleal.ReparoPlus.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    Cliente getReferenceByCpf(String cpf);

    Optional<Cliente> findByCpf(String cpf);
}
