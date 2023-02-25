package com.example.mainserver.event.controller;

import com.example.mainserver.event.model.EventDtoShort;
import com.example.mainserver.exceptions.WrongEventCreationException;
import com.example.mainserver.exceptions.WrongPatchException;
import com.example.mainserver.participation.ParticipationService;
import com.example.mainserver.event.EventService;
import com.example.mainserver.event.model.EventDto;
import com.example.mainserver.event.model.NewEventDto;
import com.example.mainserver.participation.model.ParticipationChangeStatus;
import com.example.mainserver.participation.model.ParticipationDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping
public class EventControllerUser {
    private final EventService eventService;
    private final ParticipationService participationService;


    @PostMapping("/users/{userId}/events")
    public EventDto createEvent(@PathVariable Long userId,
                                @Valid @RequestBody NewEventDto neweventDto,
                                HttpServletResponse response) {
        if (neweventDto.getCategory() == null) {
            throw new WrongEventCreationException("Field: category. Error: must not be blank. Value: null");
        }
        LocalDateTime startDate = LocalDateTime.parse(neweventDto.getEventDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        if (startDate.isBefore(LocalDateTime.now())) {
            throw new WrongPatchException("Field: eventDate. Error: должно содержать дату, которая еще не наступила. Value: " + startDate);
        }
        response.setStatus(201);
        log.info("create event by user with id {}", userId);
        return eventService.createEvent(userId, neweventDto);
    }


    @PatchMapping("/users/{userId}/events/{eventId}/requests")
    public List<ParticipationDto> confirmParticipationRequest(@PathVariable Long userId,
                                                              @PathVariable Long eventId,
                                                              @RequestBody ParticipationChangeStatus participationChangeStatus) {
        log.info("confirm participation requests {} by owner {} of event with id {}", userId, eventId, participationChangeStatus);
        return participationService.confirmParticipationRequest(userId, eventId, participationChangeStatus);


    }


    @GetMapping("/users/{userId}/events")
    public List<EventDtoShort> getEventsByUser(@PathVariable Long userId, @RequestParam(defaultValue = "0") int from,
                                               @RequestParam(defaultValue = "10") int size) {
        log.info("get events  by owner ", userId);
        return eventService.getEventByUser(userId, from, size);
    }

    @GetMapping("/users/{userId}/events/{eventId}")
    public EventDto getEventByUser(@PathVariable Long userId, @PathVariable Long eventId) {
        log.info("get events  by owner ", userId);
        return eventService.getEvent(userId, eventId);
    }


    @PatchMapping("/users/{userId}/events/{eventId}")
    public EventDto patchEventByUser(@PathVariable Long userId, @PathVariable Long eventId, @RequestBody EventDtoShort eventDtoShort) {
        log.info("putch events  by owner ", userId);
      //  return participationService.confirmParticipationRequest(userId, eventId, neweventDto);
        return eventService.putchEvent(userId, eventId, eventDtoShort );
    }


}

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
