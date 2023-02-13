package com.example.statserver.model;

public class StatMapping {

    public static ViewStats toViewStat(EndpointHit endpointHit) {
        return ViewStats.builder()
                .app(endpointHit.getApp())
                .uri(endpointHit.getUri())
                .build();
    }

}
