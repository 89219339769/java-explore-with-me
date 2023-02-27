package com.example.mainserver.participation;

import com.example.mainserver.participation.model.ParticipationChangeStatus;
import com.example.mainserver.participation.model.ParticipationDto;
import com.example.mainserver.participation.model.ParticipationListDto;
import com.example.mainserver.participation.model.ParticipationRequestDto;

import java.util.List;


public interface ParticipationService {


    public ParticipationDto createParticipationRequest(Long userId, Long eventId);


    ParticipationListDto confirmParticipationRequest(Long userId, Long eventId, ParticipationChangeStatus participationChangeStatus);


    ParticipationDto cancelParticipationRequest(Long userId, Long requestId);

    List<ParticipationDto> getParticipationRequests(Long userId);

    List<ParticipationRequestDto> getRequestsByEventIdAndInitiatorId(Long eventId, Long userId);
}
