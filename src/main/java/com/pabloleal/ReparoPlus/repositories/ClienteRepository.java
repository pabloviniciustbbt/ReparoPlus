package com.pabloleal.ReparoPlus.repositories;

import com.pabloleal.ReparoPlus.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
