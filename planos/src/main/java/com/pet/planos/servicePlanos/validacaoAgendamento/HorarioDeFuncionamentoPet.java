package com.pet.planos.servicePlanos.validacaoAgendamento;

import com.pet.planos.DTO.DadosClienteDTO;
import com.pet.planos.DTO.EssencialDTO;
import com.pet.planos.infra.Validacoes;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class HorarioDeFuncionamentoPet implements ValidadorAgenda {

    public void validar(EssencialDTO essencialDTO){
        var dadosAgenda = essencialDTO.inicio();

        var domingo = dadosAgenda.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAbertura = dadosAgenda.getHour() < 7;
        var depoisDoFechamento = dadosAgenda.getHour() > 21;
        if( domingo || antesDaAbertura || depoisDoFechamento ){
            throw new Validacoes("Agendamento fora do horario de funcionamento da loja");
        }
    }
}
