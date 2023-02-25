package com.example.mainserver.participation;

import com.example.mainserver.event.EventRepository;
import com.example.mainserver.event.model.Event;
import com.example.mainserver.event.model.NewEventDto;
import com.example.mainserver.exceptions.EventNotFoundException;
import com.example.mainserver.exceptions.ParticipationNoFoundException;
import com.example.mainserver.exceptions.WrongPatchException;
import com.example.mainserver.participation.model.Participation;
import com.example.mainserver.participation.model.ParticipationChangeStatus;
import com.example.mainserver.participation.model.ParticipationDto;
import com.example.mainserver.participation.model.ParticipationMapper;
import com.example.mainserver.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
            throw new WrongPatchException("participation request already exist");
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
            throw new WrongPatchException("requester can`t be initiator of event");
        }
        if (!participation.getEvent().getState().equals(PUBLISHED)) {
            throw new WrongPatchException("event not published");
        }

        List<Participation> listPart = participationRepository.getParticipations(eventId);

        if (participation.getEvent().getParticipantLimit() <= listPart.size()) {
            throw new WrongPatchException("the limit of requests for participation has been exhausted");
        }
        if (Boolean.TRUE.equals(participation.getEvent().getRequestModeration())) {
            participation.setStatus(PENDING);
        }
        return ParticipationMapper.toParticipationDto(participationRepository.save(participation));
    }

//    @Override
//    public List<ParticipationDto> confirmParticipationRequest(Long userId, Long eventId, NewEventDto neweventDto) {
//        return null;
//    }

    @Override
    public List<ParticipationDto> confirmParticipationRequest(Long userId, Long eventId, ParticipationChangeStatus participationChangeStatus) {
        List<ParticipationDto> participations = new ArrayList<>();
        List<Integer> participationIds = participationChangeStatus.getRequestIds();

        for (Integer participationId : participationIds) {
            Participation participation = participationRepository.findById(Long.valueOf(participationId))
                    .orElseThrow(() -> new RuntimeException("event with id = " + eventId + " not found"));
            if (!participation.getStatus().equals(PENDING)) {
                throw new RuntimeException("only participation request with status pending can be approval");
            }

            List<Participation> listPart = participationRepository.getParticipations(eventId);
            if (participation.getEvent().getParticipantLimit() <= listPart.size()) {
                throw new WrongPatchException("лимит участников исчерпан");
            } else {
                participation.setStatus(participationChangeStatus.getStatus());
            }
            participations.add(ParticipationMapper.toParticipationDto(participation));
        }
        return participations;
    }

    @Override
    public List<ParticipationDto> getParticipationRequesByInitiator(Long userId, Long eventId) {

        List<Participation> part = participationRepository.findAllByRequesterId(52L);
        List<ParticipationDto> partDto = new ArrayList<>();
        for (Participation participation : part) {
            partDto.add(ParticipationMapper.toParticipationDto(participation));
        }
        return partDto;
    }

    @Override
    public ParticipationDto cancelParticipationRequest(Long userId, Long reqId) {
        Participation participation = participationRepository.canselParticipationRequest(reqId, userId);
        if (participation == null)
            throw new ParticipationNoFoundException("Request with id=" + reqId + " was not found");
        participation.setStatus(CANCELED);
        return ParticipationMapper.toParticipationDto(participationRepository.save(participation));
    }

    @Override
    public List<ParticipationDto> getParticipationRequests(Long userId) {
        List<Participation> list = participationRepository.findAllByRequesterId(userId);
        List<ParticipationDto> listDto = new ArrayList<>();
        for (Participation participation : list) {
            listDto.add(ParticipationMapper.toParticipationDto(participation));
        }
        return listDto;
    }
}
