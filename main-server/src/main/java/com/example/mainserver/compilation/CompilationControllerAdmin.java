package com.example.mainserver.compilation;


import com.example.mainserver.compilation.model.Compilation;
import com.example.mainserver.compilation.model.CompilationDto;
import com.example.mainserver.compilation.model.CompilationDtoShort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/admin/compilations")
public class CompilationControllerAdmin {
    private final CompilationService compilationService;

    public CompilationControllerAdmin(CompilationService compilationService) {
        this.compilationService = compilationService;
    }

    @PostMapping
    public CompilationDtoShort createCompilation(@Valid @RequestBody CompilationDto compilationDto) {
        log.info("create new compilation");

        CompilationDtoShort compilationDtoShort = compilationService.createCompilation(compilationDto);
        return compilationDtoShort;
    }
//
//    @DeleteMapping("/{id}")
//    public void deleteCompilation(@PathVariable Long id) {
//        log.info("delete compilation with id {}", id);
//        compilationService.deleteCompilation(id);
//    }
//
//    @DeleteMapping("/{id}/events/{eventId}")
//    public void deleteEventFromCompilation(@PathVariable Long id,
//                                           @PathVariable Long eventId) {
//        log.info("delete event with id {} from compilation with id {}", eventId, id);
//        compilationService.deleteEventFromCompilation(id, eventId);
//    }
//
//    @PatchMapping("/{id}/events/{eventId}")
//    public void addEventToCompilation(@PathVariable Long id,
//                                      @PathVariable Long eventId) {
//        log.info("add event with id {} to compilation with id {}", eventId, id);
//        compilationService.addEventToCompilation(id, eventId);
//    }
//
//    @DeleteMapping("/{id}/pin")
//    public void deleteCompilationFromMainPage(@PathVariable Long id) {
//        log.info("delete compilation with id {} from main page", id);
//        compilationService.deleteCompilationFromMainPage(id);
//    }
//
//    @PatchMapping("/{id}/pin")
//    public void addCompilationToMainPage(@PathVariable Long id) {
//        log.info("add compilation with id {} to main page", id);
//        compilationService.addCompilationToMainPage(id);
//    }
}