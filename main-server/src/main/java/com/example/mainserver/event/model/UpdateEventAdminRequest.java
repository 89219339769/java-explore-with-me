package com.example.mainserver.event.model;

import com.example.mainserver.location.model.Location;
import com.example.mainserver.location.model.LocationDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateEventAdminRequest {
    private String annotation;

    private Long category;

    private String description;

    private String eventDate;

    private LocationDto location;

    private Boolean paid;

    private Integer participantLimit;

    private Boolean requestModeration;

    private State state;

    private String stateAction;
    private String title;
}