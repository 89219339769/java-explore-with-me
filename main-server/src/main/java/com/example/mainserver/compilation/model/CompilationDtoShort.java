package com.example.mainserver.compilation.model;


import com.example.mainserver.event.model.EventDto;
import com.example.mainserver.event.model.EventDtoShort;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompilationDtoShort {
//    private Long id;
//
//    private String title;
//
//    private Boolean pinned;

    private List<EventDtoShort> events;
}