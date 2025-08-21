package com.pet.planos.DTO;

import java.time.LocalDateTime;

//Relatórios de agendamentos (por período, por cliente, por tipo de serviço)
public record RelarioAgendamentoDTO(
        String nomeDog,
        String tipoServico,
        String raca,
        String nomeCliente,
        LocalDateTime inicio,
        LocalDateTime fim
) {
}
