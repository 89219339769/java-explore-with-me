package com.example.mainserver.compilation.model;

import com.example.mainserver.event.EventRepository;
import com.example.mainserver.event.model.Event;
import com.example.mainserver.event.model.EventDtoShort;
import com.example.mainserver.event.model.EventMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class CompilationMapper {

    private final EventRepository eventRepository;


    public Compilation toCompilation(CompilationDto compilationDto) {

        List<Long> evendsId = compilationDto.getEvents();
        List<Event> events = eventRepository.findAllById(evendsId);


        return Compilation
                .builder()
                .id(compilationDto.getId())
                .title(compilationDto.getTitle())
                .pinned(compilationDto.getPinned())
                .events(events)
                .build();
    }


    public CompilationDtoShort toCompilationDtoShort(Compilation compilation) {

        List<Event> events = compilation.getEvents();


        List<EventDtoShort> eventsDtpShort = new ArrayList<>();
        for (Event event : events)
            eventsDtpShort.add(EventMapper.toEventDtoShort(event));

            return CompilationDtoShort
                    .builder()
                    .id(compilation.getId())
                    .title(compilation.getTitle())
                    .pinned(compilation.getPinned())
                    .events(eventsDtpShort)
                    .build();
    }


}