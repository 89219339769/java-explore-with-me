package com.example.mainserver.compilation.controller;

import com.example.mainserver.compilation.CompilationService;
import com.example.mainserver.compilation.model.CompilationDto;
import com.example.mainserver.compilation.model.CompilationDtoShort;
import com.example.mainserver.exceptions.WrongCompilationCreation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    public CompilationDtoShort createCompilation(@Valid @RequestBody CompilationDto compilationDto,
                                                 HttpServletResponse httpResponse) {
        httpResponse.setStatus(201);
        log.info("create new compilation");


        if (compilationDto.getTitle() == null) {
            throw new WrongCompilationCreation("Field: title. Error: must not be blank. Value: null");
        }
        CompilationDtoShort compilationDtoShort = compilationService.createCompilation(compilationDto);
        return compilationDtoShort;
    }

    @DeleteMapping("/{compId}")
    public void deleteCompilation(@PathVariable Long compId, HttpServletResponse httpResponse) {
        log.info("delete compilation with id {}", compId);
        httpResponse.setStatus(204);
        compilationService.deleteCompilation(compId);
    }


    @PatchMapping("/{compId}")
    public CompilationDtoShort patchCompilation(@PathVariable Long compId, @Valid @RequestBody CompilationDto compilationDto) {
        log.info("patch compilation with id {}", compId);
        return compilationService.putch(compId, compilationDto);
    }

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