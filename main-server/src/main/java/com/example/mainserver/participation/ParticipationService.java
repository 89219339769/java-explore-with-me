package com.example.mainserver.participation;

import com.example.mainserver.participation.model.ParticipationDto;
import org.springframework.stereotype.Service;


public interface ParticipationService {



    public ParticipationDto createParticipationRequest(Long userId, Long eventId);



}
