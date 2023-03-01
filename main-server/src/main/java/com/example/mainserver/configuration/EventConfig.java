package com.example.mainserver.configuration;

import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import eventcl.EventClient;

@Configuration
@AllArgsConstructor
public class EventConfig {

    @Bean
    EventClient eventClient() {
        return new EventClient("http://localhost:9090", new RestTemplateBuilder());
    }
}