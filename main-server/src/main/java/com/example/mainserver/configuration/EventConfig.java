package com.example.mainserver.configuration;

import eventcl.EventClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class EventConfig {

    @Bean
    public EventClient eventClient(@Value("${stats-server.url}") String serverUrl) {
        return new EventClient(serverUrl, new RestTemplateBuilder());
    }
}