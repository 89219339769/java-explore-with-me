package com.example.mainserver.participation.controller;

import com.example.mainserver.participation.ParticipationServiceImpl;
import com.example.mainserver.participation.model.ParticipationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping
public class ParticipationControllerUser {
    private final ParticipationServiceImpl participationService;

    public ParticipationControllerUser(ParticipationServiceImpl participationService) {
        this.participationService = participationService;
    }

//    @GetMapping
//    public List<ParticipationDto> getParticipationRequests(@PathVariable Long userId) {
//        log.info("get user's {} participation requests", userId);
//        return participationService.getParticipationRequests(userId);
//    }

    @PostMapping("/users/{userId}/requests")
    public ParticipationDto createParticipationRequest(@PathVariable Long userId,
                                                       @RequestParam Long eventId) {
        log.info("create participation request by user {} to event {}", userId, eventId);
        return participationService.createParticipationRequest(userId, eventId);
    }


    @PatchMapping("/users/{userId}/requests")
    public ParticipationDto approveParticipationRequest(@PathVariable Long userId,
                                                       @RequestParam Long eventId) {
        log.info("create participation request by user {} to event {}", userId, eventId);
        return participationService.createParticipationRequest(userId, eventId);
    }











    @GetMapping("/users/{userId}/events/{eventId}/request")
    public ParticipationDto getParticipationRequest(@PathVariable Long userId,
                                                    @PathVariable Long eventId) {
        log.info("get participation request by user {} to event {}", userId, eventId);
        return participationService.getParticipationRequest(userId, eventId);
    }



}