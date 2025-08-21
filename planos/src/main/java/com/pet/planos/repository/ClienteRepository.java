package com.pet.planos.repository;

import com.pet.planos.entity.EntityCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<EntityCliente, Long> {

    Optional<EntityCliente> findByCpf (String cpf);
    Optional<EntityCliente> findByNome (String nome);
}
