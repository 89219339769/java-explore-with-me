package com.example.statserver.service;

import com.example.statserver.model.EndpointHit;
import com.example.statserver.model.ViewStats;


import java.util.List;

public interface StatService {
    EndpointHit saveStat(EndpointHit endpointHit);

    List<ViewStats> getStat(String start, String end, List<String> uris, boolean unique);
}
