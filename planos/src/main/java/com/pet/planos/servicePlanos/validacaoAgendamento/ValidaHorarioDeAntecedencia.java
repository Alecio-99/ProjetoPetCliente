package com.pet.planos.servicePlanos.validacaoAgendamento;

import com.pet.planos.DTO.DadosClienteDTO;
import com.pet.planos.DTO.EssencialDTO;
import com.pet.planos.infra.Validacoes;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component
public class ValidaHorarioDeAntecedencia implements  ValidadorAgenda{

    public void validar (EssencialDTO essencialDTO){
        var dataAgendamento = essencialDTO.inicio();
        var agora = LocalDateTime.now();
        var diferencaEntreMinutos = Duration.between(agora, dataAgendamento).toMinutes();

      if(diferencaEntreMinutos < 60){
         throw new Validacoes("O agendamento deve ser marcado com 1 hora de antecedência");
      }
    }
}
