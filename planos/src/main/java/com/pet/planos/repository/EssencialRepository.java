package com.pet.planos.repository;

import com.pet.planos.DTO.RelatorioFinanceiroDTO;
import com.pet.planos.entity.EntityPlanoEssencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EssencialRepository extends JpaRepository<EntityPlanoEssencial, Long> {
    @Query("""
        SELECT a FROM EntityPlanoEssencial a
        WHERE (:inicio IS NULL OR a.inicio >= :inicio)
        AND (:fim IS NULL OR a.fim <= :fim)
        AND (:clienteId IS NULL OR a.dono.id = :clienteId)
        AND (:tipoServico IS NULL OR LOWER(a.tipoServico) = LOWER(:tipoServico))
        AND (:raca IS NULL OR LOWER(a.raca) = LOWER(:raca))
    """)
    List<EntityPlanoEssencial> buscarRelatorio(LocalDate inicio, LocalDate fim, Long clienteId, String tipoServico, String raca);

    //Relatórios financeiros (por mês, por serviço)
    @Query("""
        SELECT e.tipoServico,
               FUNCTION('YEAR', e.inicio),
               FUNCTION('MONTH', e.inicio),
               SUM(e.preco)
        FROM EntityPlanoEssencial e
        WHERE (:inicio IS NULL OR e.inicio >= :inicio)
          AND (:fim IS NULL OR e.fim <= :fim)
          AND (:tipoServico IS NULL OR LOWER(e.tipoServico) = LOWER(:tipoServico))
        GROUP BY e.tipoServico, FUNCTION('YEAR', e.inicio), FUNCTION('MONTH', e.inicio)
    """)
    List<Object[]> gerarRelatorioFinanceiro(
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim,
            @Param("tipoServico") String tipoServico
    );
}
