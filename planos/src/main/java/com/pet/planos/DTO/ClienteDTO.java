package com.pet.planos.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClienteDTO(
       @NotNull
       String nome,
       @NotBlank
       @Email
       String email,
       @NotBlank
       @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
       String cpf,
       @NotBlank
       String telefone,
       @NotBlank
       String cep,
       @NotBlank
       String endereco,
       @NotBlank
       String  password
) {
}
