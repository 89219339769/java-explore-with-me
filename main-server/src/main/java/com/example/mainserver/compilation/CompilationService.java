package com.example.mainserver.compilation;

import com.example.mainserver.compilation.model.CompilationDto;
import com.example.mainserver.compilation.model.CompilationDtoShort;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompilationService {

    CompilationDtoShort createCompilation(CompilationDto compilationDto);

    List<CompilationDtoShort> getCompilations(Boolean pinned, int from, int size, Pageable pageable);

    CompilationDtoShort getCompilation(Long compId);

    void deleteCompilation(Long id);

    CompilationDtoShort putch(Long compId, CompilationDto compilationDto);

}