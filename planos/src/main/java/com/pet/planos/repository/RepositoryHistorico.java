package com.pet.planos.repository;

import com.pet.planos.entity.EntityEssencialHistorico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryHistorico extends JpaRepository<EntityEssencialHistorico, Long> {
}
