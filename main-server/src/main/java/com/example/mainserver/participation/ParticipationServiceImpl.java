package com.example.mainserver.participation;


import com.example.mainserver.event.EventRepository;
import com.example.mainserver.event.model.Event;
import com.example.mainserver.participation.model.Participation;
import com.example.mainserver.participation.model.ParticipationDto;
import com.example.mainserver.participation.model.ParticipationMapper;
import com.example.mainserver.user.UserRepository;
import com.example.mainserver.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.mainserver.event.model.State.PUBLISHED;
import static com.example.mainserver.participation.model.StatusRequest.*;

@Service
@AllArgsConstructor
public class ParticipationServiceImpl implements ParticipationService {

    private final ParticipationRepository participationRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;


    @Override
    public ParticipationDto createParticipationRequest(Long userId, Long eventId) {
        if (participationRepository.findByEventIdAndRequesterId(eventId, userId) != null) {
            throw new RuntimeException("participation request already exist");
        }

        Participation participation = Participation
                .builder()
                .created(LocalDateTime.now())
                .event(eventRepository.findById(eventId)
                        .orElseThrow(() -> new RuntimeException("user with id = " + userId + " not found")))
                .requester(userRepository.findById(userId)
                        .orElseThrow(() -> new RuntimeException("user with id = " + userId + " not found")))
                .status(CONFIRMED)
                .build();
        if (userId.equals(participation.getEvent().getInitiator().getId())) {
            throw new RuntimeException("requester can`t be initiator of event");
        }
        if (!participation.getEvent().getState().equals(PUBLISHED)) {
            throw new RuntimeException("event not published");
        }

        List<Participation> listPart = participationRepository.getParticipations(eventId);

        if (participation.getEvent().getParticipantLimit() <= listPart.size()) {
            throw new RuntimeException("the limit of requests for participation has been exhausted");
        }
        if (Boolean.TRUE.equals(participation.getEvent().getRequestModeration())) {
            participation.setStatus(PENDING);
        }
        return ParticipationMapper.toParticipationDto(participationRepository.save(participation));
    }

    @Override
    public ParticipationDto confirmParticipationRequest(Long eventId, Long userId, Long reqId) {
        Participation participation = participationRepository.findById(reqId)
                .orElseThrow(() -> new RuntimeException("event with id = " + eventId + " not found"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("event with id = " + eventId + " not found"));

        if (!participation.getStatus().equals(PENDING)) {
            throw new RuntimeException("only participation request with status pending can be approval");
        }
        List<Participation> listPart = participationRepository.getParticipations(eventId);

        if (participation.getEvent().getParticipantLimit() <= listPart.size()) {
            participation.setStatus(REJECTED);

        } else {
            participation.setStatus(CONFIRMED);
        }
        return ParticipationMapper.toParticipationDto(participationRepository.save(participation));
    }
}