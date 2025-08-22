package com.pet.planos.controller;

import com.pet.planos.DTO.ClienteDTO;
import com.pet.planos.DTO.DetalhesClienteDTO;
import com.pet.planos.entity.EntityCliente;
import com.pet.planos.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("cliente")
public class ControllerClientes {

    @Autowired
    private ClienteRepository clienteRepository;

    //Api para cadastro dos clientes que nossos clientes vão ter
    @PostMapping
    @Transactional
    public ResponseEntity cadastraCliente (@RequestBody @Valid ClienteDTO clienteDTO, UriComponentsBuilder uriComponentsBuilder){
          var cliente = new EntityCliente(clienteDTO);
          clienteRepository.save(cliente);

         var uri = uriComponentsBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
         return ResponseEntity.created(uri).body(new DetalhesClienteDTO(cliente));
    }

}
