package com.pet.planos.DTO;

import jakarta.validation.constraints.Future;

import java.time.LocalDateTime;

public record DadosClienteDTO (

        Long idCliente,

        @Future
        LocalDateTime inicio

){
}
