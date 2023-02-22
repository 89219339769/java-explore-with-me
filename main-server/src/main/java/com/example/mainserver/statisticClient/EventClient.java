package com.example.mainserver.statisticClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class EventClient extends BaseClient {
    private static final String API_PREFIX = "/hit";

    @Autowired
    public EventClient(@Value("${stats-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

//    public void createHit(EndpointHit endpointHit) {
//
//
//    }

    public ResponseEntity<Object> createHit(EndpointHit endpointHit) {

        return post("", endpointHit);
    }




//    public ResponseEntity<Object> getStats(LocalDateTime start, LocalDateTime end,
//                                           List<String> uris, Boolean unique) {
//        Map<String, Object> parameters = Map.of(
//                "start", start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                "end", end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                "uris", uris.get(0),
//                "unique", unique
//        );
//        return get("/stats?start={start}&end={end}&uris={uris}&unique={unique}", parameters);
//    }
}