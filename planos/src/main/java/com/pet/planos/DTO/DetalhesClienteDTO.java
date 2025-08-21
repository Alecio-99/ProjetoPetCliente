package com.pet.planos.DTO;

import com.pet.planos.entity.EntityCliente;

public record DetalhesClienteDTO(Long id, String nome, String email, String cpf, String telefone, String cep, String endereco) {

    public DetalhesClienteDTO(EntityCliente entityCliente){
        this(entityCliente.getId(), entityCliente.getNome(), entityCliente.getEmail(), entityCliente.getCpf(), entityCliente.getTelefone(), entityCliente.getCep(), entityCliente.getEndereco());
    }
}
