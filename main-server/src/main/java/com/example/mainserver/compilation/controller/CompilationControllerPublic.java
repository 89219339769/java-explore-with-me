package com.example.mainserver.compilation.controller;

import com.example.mainserver.compilation.CompilationService;
import com.example.mainserver.compilation.model.CompilationDto;
import com.example.mainserver.compilation.model.CompilationDtoShort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/compilations")
public class CompilationControllerPublic {
    private final CompilationService compilationService;

    public CompilationControllerPublic(CompilationService compilationService) {
        this.compilationService = compilationService;
    }


    @GetMapping
    public List<CompilationDtoShort> getCompilations(@RequestParam (required = false) Boolean pinned,
                                                         @RequestParam (defaultValue = "0") int from,
                                                         @RequestParam (defaultValue = "10") int size,
                                                         Pageable pageable) {
        log.info("get compilations with param: pinned = {}, from = {}, size = {}", pinned, from, size);

       return  compilationService.getCompilations(pinned, from, size, pageable);

    }












}
