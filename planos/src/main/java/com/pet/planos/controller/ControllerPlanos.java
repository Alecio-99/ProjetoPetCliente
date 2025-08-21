package com.pet.planos.controller;

import com.pet.planos.DTO.*;
import com.pet.planos.entity.EntityCliente;
import com.pet.planos.entity.EntityPlanoEssencial;
import com.pet.planos.servicePlanos.ServicePlanos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("planos")
public class ControllerPlanos {

    @Autowired
    ServicePlanos servicePlanos;

    //Agendamento
    @PostMapping("/essencial")
    public ResponseEntity<List<EntityPlanoEssencial>> criarAgenda (@RequestBody MultiplosAgendamentosDTO multiplosAgendamentosDTO){
        List<EntityPlanoEssencial> result = servicePlanos.agendaEssencial(multiplosAgendamentosDTO);
        return ResponseEntity.ok(result);
    }
    //Relatórios de agendamentos (por período, por cliente, por tipo de serviço)
    @GetMapping("/agendamentos")
    public List<RelarioAgendamentoDTO> getRelatorio(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim,
            @RequestParam(required = false) Long clienteId,
            @RequestParam(required = false) String tipoServico,
            @RequestParam(required = false) String raca
    ){
        return servicePlanos.relatorioAgendamento(inicio, fim, clienteId, tipoServico, raca);
    }
    //Api para cadastro dos clientes que nossos clientes vão ter
    @PostMapping("/cliente")
    public EntityCliente cadastraCliente (@RequestBody ClienteDTO clienteDTO){
        return servicePlanos.cadastraCliente(clienteDTO);
    }
    //teste
    @GetMapping("/essencial")
    public ResponseEntity<String> acessarPlanoEssencial() {
        return ResponseEntity.ok("Você acessou o plano Essencial!");
    }
    @GetMapping("/relatorio-financeiro")
    public List<RelatorioFinanceiroDTO> gerarRelatorio (
            @RequestParam( required = false) LocalDate inicio,
            @RequestParam( required = false) LocalDate fim,
            @RequestParam ( required = false) String tipoServico
            ){
        return servicePlanos.relatorioFinanceiro(inicio, fim, tipoServico);
    }

}
