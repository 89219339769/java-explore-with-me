package com.example.mainserver.event.model;

import com.example.mainserver.location.model.LocationMapping;
import com.example.mainserver.user.model.UserMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.example.mainserver.event.model.State.PENDING;

public class EventMapper {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static Event toEvent(NewEventDto eventDto) {
        return Event
                .builder()
                .annotation(eventDto.getAnnotation())
                .description(eventDto.getDescription())
                .eventDate(LocalDateTime.parse(eventDto.getEventDate(),
                        DATE_TIME_FORMATTER))
                .paid(eventDto.getPaid())
                .participantLimit(eventDto.getParticipantLimit())
                .requestModeration(eventDto.getRequestModeration())
                .title(eventDto.getTitle())
                .state(PENDING)
                .createdOn(LocalDateTime.now())
                .build();
    }

    public static EventDto toEventDto(Event event) {
        return EventDto
                .builder()
                .id(event.getId())
                .annotation(event.getAnnotation())
                .category(event.getCategory())
                .createdOn(event.getCreatedOn().format(DATE_TIME_FORMATTER))
                .description(event.getDescription())
                .eventDate(event.getEventDate().format(DATE_TIME_FORMATTER))
                .initiator(UserMapping.toUserDto(event.getInitiator()))
                .location(LocationMapping.toLocationDto(event.getLocation()))
                .paid(event.getPaid())
                .participantLimit(event.getParticipantLimit())
                .publishedOn(event.getPublishedOn())
                .requestModeration(event.getRequestModeration())
                .state(event.getState())
                .title(event.getTitle())
                .views(event.getViews())
                .build();
    }


    public static EventDtoShort toEventDtoShort(Event event) {
        return EventDtoShort
                .builder()
                .id(event.getId())
                .annotation(event.getAnnotation())
                .category(event.getCategory())
                .eventDate(event.getEventDate().format(DATE_TIME_FORMATTER))
                .initiator(UserMapping.toUserDto(event.getInitiator()))
                .paid(event.getPaid())
                .title(event.getTitle())
                .views(event.getViews())
                .build();
    }
}
