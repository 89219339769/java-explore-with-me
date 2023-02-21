package com.example.mainserver.event.controller;

import com.example.mainserver.event.EventService;
import com.example.mainserver.event.model.EventDto;
import com.example.mainserver.event.model.EventDtoShort;
import com.example.mainserver.statisticClient.EventClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/events")
public class  EventControllerPublic {
    private final EventService eventService;
    private final EventClient eventClient;

    public EventControllerPublic(EventService eventService, EventClient eventClient) {
        this.eventService = eventService;
        this.eventClient = eventClient;
    }
    @GetMapping
    public List<EventDtoShort> getEvents(@RequestParam(required = false) String text,
                                         @RequestParam(required = false) List<Long> categoryIds,
                                         @RequestParam(required = false) Boolean paid,
                                         @RequestParam(required = false) String rangeStart,
                                         @RequestParam(required = false) String rangeEnd,
                                         @RequestParam(required = false) String sort,
                                         @RequestParam (defaultValue = "0") int from,
                                         @RequestParam (defaultValue = "10") int size,
                                         HttpServletRequest httpServletRequest) {
        log.info("get events by param: text = {}, categoryIds = {}, paid = {}, rangeStart = {}, rangeEnd = {}, " +
                        "onlyAvailable = {}, sort = {}, from = {}, size = {}", text, categoryIds, paid, rangeStart, rangeEnd,
                 sort, from, size);
      //  eventClient.createHit(httpServletRequest);
        return eventService.getEventsPublic(text, categoryIds, paid, rangeStart, rangeEnd, sort, from, size);
    }



    @GetMapping("/{id}")
    public EventDto getEvent(@PathVariable Long id) {
        log.info("get event with id {}", id);
     //   eventClient.createHit(httpServletRequest);
        return eventService.getEventPublic(id);
    }
}
