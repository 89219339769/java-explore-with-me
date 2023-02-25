package com.example.mainserver.participation.controller;

import com.example.mainserver.participation.ParticipationServiceImpl;
import com.example.mainserver.participation.model.ParticipationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@RestController
@RequestMapping
public class ParticipationControllerUser {
    private final ParticipationServiceImpl participationService;

    public ParticipationControllerUser(ParticipationServiceImpl participationService) {
        this.participationService = participationService;
    }

    @GetMapping("/users/{userId}/requests")
    public List<ParticipationDto> getParticipationRequests(@PathVariable Long userId) {
        log.info("get user's {} participation requests", userId);
        return participationService.getParticipationRequests(userId);
    }

    @PostMapping("/users/{userId}/requests")
    public ParticipationDto createParticipationRequest(@PathVariable Long userId,
                                                       @RequestParam Long eventId,
                                                       HttpServletResponse response) {
        log.info("create participation request by user {} to event {}", userId, eventId);
        response.setStatus(201);
        return participationService.createParticipationRequest(userId, eventId);
    }


    @PatchMapping("/users/{userId}/requests")
    public ParticipationDto approveParticipationRequest(@PathVariable Long userId,
                                                        @RequestParam Long eventId) {
        log.info("create participation request by user {} to event {}", userId, eventId);
        return participationService.createParticipationRequest(userId, eventId);
    }


    @PatchMapping("/users/{userId}/requests/{requestId}/cancel")
    public ParticipationDto cancelParticipationRequest(@PathVariable Long userId,
                                                       @PathVariable Long requestId) {
        log.info("cancel participation request {} by user {}", requestId, userId);
        return participationService.cancelParticipationRequest(userId, requestId);
    }


//    @GetMapping("/users/{userId}/events/{eventId}/requests")
//    public ParticipationDto getParticipationRequestbyInitiator(@PathVariable Long userId,
//                                                               @PathVariable Long eventId) {
//        log.info("get participation request by user {} to event {}", userId, eventId);
//        return participationService.getParticipationRequest(userId, eventId);
//    }


    @GetMapping("/users/{userId}/events/{eventId}/requests")
    public List<ParticipationDto> getRequestsByEventIdAndInitiatorId(@PathVariable Long userId,
                                                               @PathVariable Long eventId) {
        log.info("get participation request by user {} to event {}", userId, eventId);
        return participationService.getParticipationRequesByInitiator(userId, eventId);
    }


}
