package com.example.mainserver.participation;

import com.example.mainserver.participation.model.ParticipationChangeStatus;
import com.example.mainserver.participation.model.ParticipationDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ParticipationService {



    public ParticipationDto createParticipationRequest(Long userId, Long eventId);


   List<ParticipationDto> confirmParticipationRequest(Long userId, Long eventId, ParticipationChangeStatus participationChangeStatus);

    ParticipationDto getParticipationRequest(Long userId, Long eventId);

    ParticipationDto cancelParticipationRequest(Long userId, Long requestId);
}
