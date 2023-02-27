package com.example.mainserver.event.model;

import com.example.mainserver.category.model.Category;
import com.example.mainserver.location.model.Location;
import com.example.mainserver.user.model.UserDto;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDtoShort {
    private Long id;

    private String annotation;

    private Location location;

    private Integer confirmedRequests;

    private Category category;

    private String descripsion;
    private String eventDate;

    private UserDto initiator;

    private int ParticipantLimit;
    private Boolean paid;

    private boolean requestModeration;
    private String title;

    private String stateAction;

    private Long views;


}