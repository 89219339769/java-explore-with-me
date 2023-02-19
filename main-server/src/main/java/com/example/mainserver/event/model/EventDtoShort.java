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
public class EventDtoShort {
    private Long id;

    private String annotation;


    private Integer confirmedRequests;

    private Category category;


    private String eventDate;

    private UserDto initiator;


    private Boolean paid;


    private String title;

    private Long views;



}