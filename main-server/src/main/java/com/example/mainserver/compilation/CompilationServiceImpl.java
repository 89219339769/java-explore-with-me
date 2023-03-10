package com.example.mainserver.compilation;

import com.example.mainserver.compilation.model.Compilation;
import com.example.mainserver.compilation.model.CompilationDto;
import com.example.mainserver.compilation.model.CompilationDtoShort;
import com.example.mainserver.compilation.model.CompilationMapper;
import com.example.mainserver.event.EventRepository;
import com.example.mainserver.event.model.Event;
import com.example.mainserver.exceptions.CompilationNotFounfExeption;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<CompilationDtoShort> getCompilations(Boolean pinned, int from, int size, Pageable pageable) {

        if (pinned == null) {
            return getCompilationWithOutPinned(from, size, pageable);
        }
        pageable = PageRequest.of(from, size, Sort.by(Sort.Direction.ASC, "id"));
        Page<Compilation> compilations = compilationRepository.findAll(pageable);

        List<Compilation> compilationsList = compilations.getContent();
        List<CompilationDtoShort> compilationDtoList = new ArrayList<>();
        List<CompilationDtoShort> compilationDtoListWithPinned = new ArrayList<>();

        for (Compilation compilation : compilationsList) {
            compilationDtoList.add(compilationMapper.toCompilationDtoShort(compilation));
        }
        for (CompilationDtoShort compilationDtoShort : compilationDtoList) {
            if (compilationDtoShort.getPinned() == true) {
                compilationDtoListWithPinned.add(compilationDtoShort);
            }
        }
        return compilationDtoListWithPinned;
    }

    @Override
    public CompilationDtoShort getCompilation(Long compId) {

        Compilation compilation = compilationRepository.findById(compId)
                .orElseThrow(() -> new CompilationNotFounfExeption("compilation with id = " + compId + " not found"));
        return compilationMapper.toCompilationDtoShort(compilation);
    }

    @Override
    public void deleteCompilation(Long compId) {
        Compilation compilation = compilationRepository.findById(compId)
                .orElseThrow(() -> new CompilationNotFounfExeption("compilation with id = " + compId + " not found"));
        compilationRepository.deleteById(compId);


    }

    @Override
    public CompilationDtoShort putch(Long compId, CompilationDto compilationDto) {
        Compilation compilation = compilationRepository.findById(compId)
                .orElseThrow(() -> new CompilationNotFounfExeption("compilation with id = " + compId + " not found"));

        List<Event> events = new ArrayList<>();
        for (Long compilId : compilationDto.getEvents()) {
            events.add(eventRepository.findById(compilId)
                    .orElseThrow(() -> new CompilationNotFounfExeption("compilation with id = " + compilId + " not found")));
        }

        if (compilationDto.getEvents() != null) {
            compilation.setEvents(events);
        }
        if (compilationDto.getPinned() != null) {
            compilation.setPinned(compilationDto.getPinned());
        }
        if (compilationDto.getTitle() != null)
            compilation.setTitle(compilationDto.getTitle());
        compilationRepository.save(compilation);
        CompilationDtoShort compilationDtoShort = compilationMapper.toCompilationDtoShort(compilation);
        return compilationDtoShort;
    }


    private List<CompilationDtoShort> getCompilationWithOutPinned(int from, int size, Pageable pageable) {
        pageable = PageRequest.of(from, size, Sort.by(Sort.Direction.ASC, "id"));
        Page<Compilation> compilations = compilationRepository.findAll(pageable);

        List<Compilation> compilationsList = compilations.getContent();
        List<CompilationDtoShort> compilationDtoList = new ArrayList<>();

        for (Compilation compilation : compilationsList) {
            compilationDtoList.add(compilationMapper.toCompilationDtoShort(compilation));
        }
        return compilationDtoList;
    }
}


