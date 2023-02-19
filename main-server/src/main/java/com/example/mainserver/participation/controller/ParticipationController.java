package com.example.mainserver.participation.controller;

import com.example.mainserver.participation.ParticipationServiceImpl;
import com.example.mainserver.participation.model.ParticipationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/users/{userId}/requests")
public class ParticipationController {
    private final ParticipationServiceImpl participationService;

    public ParticipationController(ParticipationServiceImpl participationService) {
        this.participationService = participationService;
    }

//    @GetMapping
//    public List<ParticipationDto> getParticipationRequests(@PathVariable Long userId) {
//        log.info("get user's {} participation requests", userId);
//        return participationService.getParticipationRequests(userId);
//    }

    @PostMapping
    public ParticipationDto createParticipationRequest(@PathVariable Long userId,
                                                       @RequestParam Long eventId) {
        log.info("create participation request by user {} to event {}", userId, eventId);
        return participationService.createParticipationRequest(userId, eventId);
    }


    @PatchMapping
    public ParticipationDto approveParticipationRequest(@PathVariable Long userId,
                                                       @RequestParam Long eventId) {
        log.info("create participation request by user {} to event {}", userId, eventId);
        return participationService.createParticipationRequest(userId, eventId);
    }




//    @PatchMapping("/{requestId}/cancel")
//    public ParticipationDto cancelParticipationRequest(@PathVariable Long userId,
//                                                       @PathVariable Long requestId) {
//        log.info("cancel participation request {} by user {}", requestId, userId);
//        return participationService.cancelParticipationRequest(userId, requestId);
//    }
}
