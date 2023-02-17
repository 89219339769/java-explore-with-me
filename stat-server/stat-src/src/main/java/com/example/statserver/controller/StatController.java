package com.example.statserver.controller;

import com.example.statserver.model.EndpointHit;
import com.example.statserver.model.ViewStats;
import com.example.statserver.service.StatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping
public class StatController {

    private final StatService statService;


    @PostMapping("/hit")
    public ResponseEntity create(@RequestBody EndpointHit endpointHit) {
        statService.saveStat(endpointHit);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/stats")
    public List<ViewStats> getStat(@RequestParam(name = "start") String start,
                                   @RequestParam(name = "end") String end,
                                   @RequestParam(name = "uris") List<String> uris,
                                   @RequestParam(name = "unique", defaultValue = "false") boolean unique) {


        return statService.getStat(start, end, uris, unique);
    }
}
