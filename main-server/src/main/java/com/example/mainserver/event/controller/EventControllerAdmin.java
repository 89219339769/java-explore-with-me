package com.example.mainserver.event.controller;

import com.example.mainserver.event.EventService;
import com.example.mainserver.event.model.EventDto;
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



//    @PutMapping("/{eventId}")
//    public EventDto updateEventByAdmin(@PathVariable Long eventId,
//                                       @RequestBody AdminUpdateEventDto eventDto) {
//        log.info("update event with id {} by admin", eventId);
//        return eventService.updateEventByAdmin(eventId, eventDto);
//    }

    @PatchMapping("/{eventId}/publish")
    public EventDto publishEvent(@PathVariable Long eventId) {
        log.info("publish event with id {} by admin", eventId);
        return eventService.publishEvent(eventId);
    }

//    @PatchMapping("/{eventId}/reject")
//    public EventDto rejectEvent(@PathVariable Long eventId) {
//        log.info("reject event with id {} by admin", eventId);
//        return eventService.rejectEvent(eventId);
//    }
}