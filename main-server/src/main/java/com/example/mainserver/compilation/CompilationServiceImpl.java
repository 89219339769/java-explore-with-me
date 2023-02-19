package com.example.mainserver.compilation;

import com.example.mainserver.compilation.model.Compilation;
import com.example.mainserver.compilation.model.CompilationDto;
import com.example.mainserver.compilation.model.CompilationDtoShort;
import com.example.mainserver.compilation.model.CompilationMapper;
import com.example.mainserver.event.EventRepository;
import com.example.mainserver.event.model.Event;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompilationServiceImpl implements CompilationService {
    private final CompilationRepository compilationRepository;
    private final EventRepository eventRepository;
    private final CompilationMapper compilationMapper;

    @Override
    public CompilationDtoShort createCompilation(CompilationDto compilationDto) {
        Compilation compilation = compilationMapper.toCompilation(compilationDto);
        List<Event> events = eventRepository.findAllById(compilationDto.getEvents());
        compilation.setEvents(events);
        compilationRepository.save(compilation);
        CompilationDtoShort compilationDtoShort = compilationMapper.toCompilationDtoShort(compilation);
        return compilationDtoShort;
    }

    @Override
    public CompilationDtoShort getCompilation(Long compId) {

        Compilation compilation = compilationRepository.findById(compId)
                .orElseThrow(() -> new RuntimeException("compilation with id = " + compId + " not found"));

        CompilationDtoShort compilationDtoShort = compilationMapper.toCompilationDtoShort(compilation);
        return compilationDtoShort;

    }
}
