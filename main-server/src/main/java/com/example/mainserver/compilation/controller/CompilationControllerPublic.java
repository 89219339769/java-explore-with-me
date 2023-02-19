package com.example.mainserver.compilation.controller;

import com.example.mainserver.compilation.CompilationService;
import com.example.mainserver.compilation.model.CompilationDto;
import com.example.mainserver.compilation.model.CompilationDtoShort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;





@Slf4j
@RestController
@RequestMapping("/compilations")
public class CompilationControllerPublic {
    private final CompilationService compilationService;

    public CompilationControllerPublic(CompilationService compilationService) {
        this.compilationService = compilationService;
    }

    @GetMapping("/{compId}")
    public CompilationDtoShort createCompilation(@PathVariable Long compId) {
        log.info("get compilation");

        return  compilationService.getCompilation(compId);

    }
}
//