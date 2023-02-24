package com.example.mainserver.event.controller;

import com.example.mainserver.event.EventService;
import com.example.mainserver.event.model.EventDto;
import com.example.mainserver.event.model.UpdateEventAdminRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/events")
public class EventControllerAdmin {
    private final EventService eventService;

    public EventControllerAdmin(EventService eventService) {
        this.eventService = eventService;
    }


    @PatchMapping("/{eventId}")
    public EventDto publishEvent(@PathVariable Long eventId, @RequestBody UpdateEventAdminRequest updateEventAdminRequest) {
        log.info("publish event with id {} by admin", eventId);
        return eventService.publishEvent(eventId, updateEventAdminRequest);
    }


    @GetMapping
    public List<EventDto> getEventsByAdmin(@RequestParam(required = false) List<Long> users,
                                           @RequestParam(required = false) List<String> states,
                                           @RequestParam(required = false) List<Long> categories,
                                           @RequestParam(required = false) String rangeStart,
                                           @RequestParam(required = false) String rangeEnd,
                                           @RequestParam(defaultValue = "0") int from,
                                           @RequestParam(defaultValue = "10") int size) {
        log.info("get events by admin with param: userIds = {}, states = {}, categoryIds = {}, rangeStart = {}, " +
                "rangeEnd = {}, from = {}, size = {}", users, states, categories, rangeStart, rangeEnd, from, size);
        return eventService.getEventsByAdmin(users, states, categories, rangeStart, rangeEnd, from, size);
    }
}