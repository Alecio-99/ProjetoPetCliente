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
public class PlanoProssionalImpl implements PlanoHandler {

    private final PlanoEssencialImpl planoEssencial; // Reutiliza o Essencial
    private final RestTemplate restTemplate;

    @Autowired
    public PlanoProssionalImpl(PlanoEssencialImpl planoEssencial, RestTemplate restTemplate) {
        this.planoEssencial = planoEssencial;
        this.restTemplate = restTemplate;
    }

    @Override
    public void processaPlano(Long clienteId) {

        // Primeiro, executa o que já é feito no plano Essencial
        planoEssencial.processaPlano(clienteId);

        // Depois adiciona os novos acessos do plano profissional
        String urlEstoque = "http://localhost:8083/estoque/ativar";
        String urlRelatorios = "http://localhost:8084/relatorios/ativar";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Long> requestBody = new HashMap<>();
        requestBody.put("clienteId", clienteId);
        HttpEntity<Map<String, Long>> request = new HttpEntity<>(requestBody, headers);

        restTemplate.postForEntity(urlEstoque, request, Void.class);
        restTemplate.postForEntity(urlRelatorios, request, Void.class);

        System.out.println("Acesso a Estoque e Relatórios liberado para cliente ID: " + clienteId);
    }
}
