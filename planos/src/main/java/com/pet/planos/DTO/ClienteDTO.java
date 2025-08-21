package com.pet.planos.DTO;

public record ClienteDTO(

       String nome,
       String email,
       String cpf,
       String telefone,
       String cep,
       String endereco,
       String  password
) {
}
