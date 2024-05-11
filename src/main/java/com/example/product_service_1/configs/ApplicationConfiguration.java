package com.example.product_service_1.configs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class ApplicationConfiguration {

    @Bean
    public RestTemplate creatTestTemplate(){
        return  new RestTemplate();
    }
}
