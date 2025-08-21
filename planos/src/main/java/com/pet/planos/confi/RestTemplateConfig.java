package com.pet.planos.confi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate; // importe certo

@Configuration
public class RestTemplateConfig { // nome diferente da classe

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(); // agora funciona normalmente
    }
}

