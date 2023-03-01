package com.example.mainserver.event;

import com.example.mainserver.event.model.EventDto;
import com.example.mainserver.event.model.EventDtoShort;
import com.example.mainserver.event.model.NewEventDto;
import com.example.mainserver.event.model.UpdateEventAdminRequest;

import java.util.List;

public interface EventService {

    public EventDto createEvent(Long userId, NewEventDto eventDto);

    EventDto publishEvent(Long eventId, UpdateEventAdminRequest updateEventAdminRequest);

    EventDto getEvent(Long userId, Long eventId);

    List<EventDtoShort> getEventByUser(Long userId, int from, int size);

    EventDto putchEvent(Long userId, Long eventId, EventDtoShort eventDtoShort);

    List<EventDto> getEventsByAdmin(List<Long> users, List<String> states, List<Long> categories, String rangeStart, String rangeEnd, int from, int size);

    List<EventDtoShort> getEventsPublic(String text, List<Long> categoryIds, Boolean paid, String rangeStart, String rangeEnd, String sort, int from, int size);

    EventDto getEventPublic(Long id);

}