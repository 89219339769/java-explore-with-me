package com.example.mainserver.event;

import com.example.mainserver.event.model.EventDto;
import com.example.mainserver.event.model.EventDtoShort;
import com.example.mainserver.event.model.NewEventDto;
import com.example.mainserver.event.model.UpdateEventAdminRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface EventService {
  //  List<ShortEventDto> getEvents(String text, List<Long> categoryIds, Boolean paid, String rangeStart, String rangeEnd,
              //                    Boolean onlyAvailable, String sort, int from, int size);

 //   EventDto getEvent(Long id);

  //  List<ShortEventDto> getUserEvents(Long userId, int from, int size);

  //  EventDto updateEvent(Long userId, UserUpdateEventDto eventDto);

    public EventDto createEvent(Long userId, NewEventDto eventDto);

    EventDto publishEvent(Long eventId, UpdateEventAdminRequest updateEventAdminRequest);

    EventDto getEvent(Long userId, Long eventId);

    List<EventDtoShort> getEventByUser(Long userId, int from, int size);

    EventDto putchEvent(Long userId, Long eventId, EventDtoShort eventDtoShort);

    //   EventDto getEventByUser(Long eventId, Long userId);

//    EventDto cancelEventByUser(Long eventId, Long userId);

//    List<EventDto> getEventsByAdmin(List<Long> userIds, List<String> states, List<Long> categoryIds,
  //                                  String rangeStart, String rangeEnd, int from, int size);

 //   EventDto updateEventByAdmin(Long eventId, AdminUpdateEventDto eventDto);

//    EventDto publishEvent(Long eventId);

 //   EventDto rejectEvent(Long eventId);
}