package com.pet.planos.servicePlanos;

//import com.pet.planos.confi.RestTemplate;
import org.springframework.web.client.RestTemplate;
import com.pet.planos.interfase.PlanoHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;



import java.util.HashMap;
import java.util.Map;

@Service
public class PlanoEssencialImpl implements PlanoHandler {


    private final RestTemplate restTemplate;

    @Autowired
    PlanoEssencialImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public void processaPlano(Long clienteId) {
        String url = "http://localhost:8082/banho-e-tosa/ativar";

        // Cria o corpo da requisição (pode ser um DTO, aqui é só o ID para simplificar)
        Map<String, Long> requestBody = new HashMap<>();
        requestBody.put("clienteId", clienteId);

        // (Opcional) Headers - por exemplo, para JWT
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // headers.setBearerAuth(token); // caso use JWT

        HttpEntity<Map<String, Long>> request = new HttpEntity<>(requestBody, headers);

        // Chamada POST
        restTemplate.postForEntity(url, request, Void.class);

        System.out.println("Acesso ao Banho e Tosa liberado para cliente ID: " + clienteId);
    }
}

