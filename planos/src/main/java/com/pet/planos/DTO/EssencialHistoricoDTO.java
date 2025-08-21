package com.pet.planos.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pet.planos.enuns.EnunsStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EssencialHistoricoDTO(
        String nomeDog,
        String tipoServico,
        String raca,

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime inicio,

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime fim,

        String nomeDono,

        BigDecimal preco,

        EnunsStatus status

) {}
