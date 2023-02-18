package com.example.mainserver.event.model;

import com.example.mainserver.category.model.Category;

import com.example.mainserver.location.model.LocationDto;
import com.example.mainserver.user.model.UserDto;
import lombok.*;


import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private Long id;

    private String annotation;

    private Category category;

    private String createdOn;

    private String description;

    private String eventDate;

    private UserDto initiator;

    private LocationDto location;

    private Boolean paid;

    private Integer participantLimit;

    private LocalDateTime publishedOn;

    private Boolean requestModeration;

    private State state;

    private String title;

    private Integer confirmedRequests;

    private Long views;
}