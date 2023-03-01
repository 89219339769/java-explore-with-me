package com.example.mainserver.compilation.model;

import com.example.mainserver.event.model.EventDtoShort;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompilationDtoShort {


    private List<EventDtoShort> events;

    private Long id;

    private String title;

    private Boolean pinned;


}