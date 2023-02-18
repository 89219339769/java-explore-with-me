package com.example.mainserver.event;


import com.example.mainserver.event.model.EventDto;
import com.example.mainserver.event.model.NewEventDto;
import lombok.extern.slf4j.Slf4j;
        import org.springframework.web.bind.annotation.*;
        import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/users/{userId}/events")
public class  EventControllerUser {
    private final EventService eventService;
 //   private final ParticipationService participationService;

    public  EventControllerUser(EventService eventService) {
        this.eventService = eventService;
     //   this.participationService = participationService;
    }

//    @GetMapping
//    public List<ShortEventDto> getUserEvents(@PathVariable Long userId,
//                                             @RequestParam (defaultValue = "0") int from,
//                                             @RequestParam (defaultValue = "10") int size) {
//        log.info("get events added by user with id {}", userId);
//        return eventService.getUserEvents(userId, from, size);
//    }

//    @PatchMapping
//    public EventDto updateEvent(@PathVariable Long userId,
//                                @RequestBody UserUpdateEventDto eventDto) {
//        log.info("update event by owner with id {}", userId);
//        return eventService.updateEvent(userId, eventDto);
//    }

    @PostMapping
    public EventDto createEvent(@PathVariable Long userId,
                                @Valid @RequestBody NewEventDto neweventDto) {
        log.info("create event by user with id {}", userId);
        return eventService.createEvent(userId, neweventDto);
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
}