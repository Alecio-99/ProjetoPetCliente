package com.pet.planos.servicePlanos;

import com.pet.planos.DTO.*;
import com.pet.planos.entity.EntityCliente;
import com.pet.planos.entity.EntityEssencialHistorico;
import com.pet.planos.entity.EntityPlanoEssencial;
import com.pet.planos.infra.Validacoes;
import com.pet.planos.interfase.PlanoHandler;
import com.pet.planos.repository.ClienteRepository;
import com.pet.planos.repository.EssencialRepository;
import com.pet.planos.repository.RepositoryHistorico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ServicePlanos {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EssencialRepository essencialRepository;

    @Autowired
    RepositoryHistorico repositoryHistorico;


    private final Map<String, PlanoHandler> handlerMap;

    public ServicePlanos(List<PlanoHandler> handlerList) {
        this.handlerMap = new HashMap<>();
        handlerMap.put("ESSENCIAL", findHandler(handlerList, PlanoEssencialImpl.class));
        handlerMap.put("PROFISSIONAL", findHandler(handlerList, PlanoProssionalImpl.class));
    }

    private PlanoHandler findHandler(List<PlanoHandler> list, Class<?> clazz) {
        return list.stream()
                .filter(h -> clazz.isAssignableFrom(h.getClass()))
                .findFirst()
                .orElseThrow();
    }

    public void processaPlanoCliente(Long clienteId, String tipoPlano) {
        PlanoHandler planoHandler = handlerMap.get(tipoPlano.toUpperCase());
        if (planoHandler == null) {
            throw new IllegalArgumentException("Plano não encontrado" + clienteId);
        }
        planoHandler.processaPlano(clienteId);
    }

    // Agendamento
    public List<EntityPlanoEssencial> agendaEssencial(MultiplosAgendamentosDTO multiplosAgendamentosDTO) {
        Optional<EntityCliente> donoPet = clienteRepository.findByNome(multiplosAgendamentosDTO.nomeDono());
        if (!donoPet.isPresent()) {
            System.out.println("Dono Não encontrado");
            throw new RuntimeException("Dono Não encontrado");
        }

        List<EntityPlanoEssencial> agendamentos = new ArrayList<>();

        for (EssencialDTO pet : multiplosAgendamentosDTO.pets()) {
            EntityPlanoEssencial agenda = new EntityPlanoEssencial();
            agenda.setNomeDog(pet.nomeDog());
            agenda.setTipoServico(pet.tipoServico());
            agenda.setRaca(pet.raca());
            agenda.setInicio(pet.inicio());
            agenda.setFim(pet.fim());
            agenda.setPreco(pet.preco());
            agenda.setDono(donoPet.get());
            agenda.setStatus(pet.status());

            EntityPlanoEssencial saved = essencialRepository.save(agenda);

            EntityEssencialHistorico historico = new EntityEssencialHistorico();
            historico.setNomeDog(pet.nomeDog());
            historico.setTipoServico(pet.tipoServico());
            historico.setRaca(pet.raca());
            historico.setInicio(pet.inicio());
            historico.setFim(pet.fim());
            historico.setPreco(pet.preco());
            historico.setDono(donoPet.get());
            historico.setStatus(pet.status());
            historico.setIdEssencial(saved);

            repositoryHistorico.save(historico);
        }
        return agendamentos;
    }

    //Relatórios de agendamentos (por período, por cliente, por tipo de serviço)
    public List<RelarioAgendamentoDTO> relatorioAgendamento(LocalDate inicio, LocalDate fim, Long clienteId, String tipoServico, String raca) {
        List<EntityPlanoEssencial> agendamento = essencialRepository.buscarRelatorio(inicio, fim, clienteId, tipoServico, raca);
        return agendamento.stream().map(a -> new RelarioAgendamentoDTO(
                a.getNomeDog(),
                a.getTipoServico(),
                a.getRaca(),
                a.getDono().getNome(),
                a.getInicio(),
                a.getFim()
        )).toList();
    }

    //Relatórios financeiros (por mês, por serviço)
    public List<RelatorioFinanceiroDTO> relatorioFinanceiro(LocalDate inicio, LocalDate fim, String tipoServico) {
        // Correção: Verifique se inicio e fim são nulos antes de converter
        LocalDateTime inicioDateTime = (inicio != null) ? inicio.atStartOfDay() : null;
        LocalDateTime fimDateTime = (fim != null) ? fim.atTime(23, 59, 59) : null; // Para incluir o dia inteiro

        List<Object[]> rawResults = essencialRepository.gerarRelatorioFinanceiro(inicioDateTime, fimDateTime, tipoServico);
        return rawResults.stream()
                .map(row -> new RelatorioFinanceiroDTO(
                        (String) row[0],    // tipoServico (String)
                        (Integer) row[1],   // ano (Integer)
                        (Integer) row[2],   // mesNum (Integer)
                        (BigDecimal) row[3] // total (BigDecimal)
                ))
                .collect(Collectors.toList());
    }

}