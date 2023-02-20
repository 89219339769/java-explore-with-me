package com.example.mainserver.compilation;


import com.example.mainserver.compilation.model.Compilation;
import com.example.mainserver.compilation.model.CompilationDto;
import com.example.mainserver.compilation.model.CompilationDtoShort;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompilationService {
  //  List<CompilationDto> getCompilations(Boolean pinned, int from, int size);

 //   CompilationDto getCompilation(Long id);

    CompilationDtoShort createCompilation(CompilationDto compilationDto);

    List<CompilationDtoShort> getCompilations(Boolean pinned, int from, int size, Pageable pageable);


//    void deleteCompilation(Long id);
//
//    void deleteEventFromCompilation(Long id, Long eventId);
//
//    void addEventToCompilation(Long id, Long eventId);
//
//    void deleteCompilationFromMainPage(Long id);
//
//    void addCompilationToMainPage(Long id);
}