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
    public  ResponseEntity create(@RequestBody EndpointHit endpointHit,  HttpStatus  httpStatus) {

       //    return    statService.saveStat(endpointHit);
        statService.saveStat(endpointHit);

        // первый рабочий вариант
         return new ResponseEntity<>(HttpStatus.CREATED);



       // второй вариант дает и тело и статус
       //    return (EndpointHit) new ResponseEntity<>( httpStatus.CREATED).getBody();
    }


    @GetMapping("/stats")
    public List<ViewStats> getStat(@RequestParam(name = "start") String start,
                                   @RequestParam(name = "end") String end,
                                   @RequestParam(name = "uris") List<String> uris,
                                   @RequestParam(name = "unique", defaultValue = "false") boolean unique) {


        return statService.getStat(start, end, uris, unique);
    }
}
