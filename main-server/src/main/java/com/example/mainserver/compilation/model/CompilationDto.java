package com.example.mainserver.compilation.model;

import com.example.mainserver.event.model.EventDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompilationDto {
    private Long id;

    private String title;

    private Boolean pinned;

    private List<Long> events;
}
