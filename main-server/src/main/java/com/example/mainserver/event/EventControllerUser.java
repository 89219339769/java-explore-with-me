package com.example.mainserver.event;


import com.example.mainserver.event.model.EventDto;
import com.example.mainserver.event.model.NewEventDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/users/{userId}/events")
public class  EventControllerUser {
    private final EventService eventService;
    //   private final ParticipationService participationService;

    public EventControllerUser(EventService eventService) {
        this.eventService = eventService;
        //   this.participationService = participationService;
    }

    @PostMapping
    public EventDto createEvent(@PathVariable Long userId,
                                @Valid @RequestBody NewEventDto neweventDto,
                                HttpServletResponse response) {
        log.info("create event by user with id {}", userId);
        response.setStatus(401);
        return eventService.createEvent(userId, neweventDto);
    }
}

//    @GetMapping("/{eventId}")
//    public EventDto getEventByUser(@PathVariable Long userId,
//                                   @PathVariable Long eventId) {
//        log.info("get event with id {} by owner with id {}", eventId, userId);
//        return eventService.getEventByUser(eventId, userId);
//    }

//    @PatchMapping("/{eventId}")
//    public EventDto cancelEventByUser(@PathVariable Long userId,
//                                      @PathVariable Long eventId) {
//        log.info("cancel event with id {} by owner with id {}", eventId, userId);
//        return eventService.cancelEventByUser(eventId, userId);
//    }
//
//    @GetMapping("/{eventId}/requests")
//    public List<ParticipationDto> getParticipationRequests(@PathVariable Long userId,
//                                                           @PathVariable Long eventId) {
//        log.info("get participation requests by owner {} of event with id {}", userId, eventId);
//        return participationService.getParticipationRequests(eventId, userId);
//    }
//
//    @PatchMapping("/{eventId}/requests/{reqId}/confirm")
//    public ParticipationDto confirmParticipationRequest(@PathVariable Long userId,
//                                                        @PathVariable Long eventId,
//                                                        @PathVariable Long reqId) {
//        log.info("confirm participation requests {} by owner {} of event with id {}", reqId, userId, eventId);
//        return participationService.confirmParticipationRequest(eventId, userId, reqId);
//    }
//
//    @PatchMapping("/{eventId}/requests/{reqId}/reject")
//    public ParticipationDto rejectParticipationRequest(@PathVariable Long userId,
//                                                       @PathVariable Long eventId,
//                                                       @PathVariable Long reqId) {
//        log.info("reject participation requests {} by owner {} of event with id {}", reqId, userId, eventId);
//        return participationService.rejectParticipationRequest(eventId, userId, reqId);
//    }
