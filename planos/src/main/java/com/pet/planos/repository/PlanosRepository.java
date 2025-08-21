package com.pet.planos.repository;

import com.pet.planos.entity.PlanosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanosRepository extends JpaRepository<PlanosEntity, Long> {
    Optional<PlanosEntity> findByNome(String nome);
}
