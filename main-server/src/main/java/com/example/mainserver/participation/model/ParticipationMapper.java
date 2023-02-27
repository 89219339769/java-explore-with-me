package com.example.mainserver.participation.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.mainserver.event.model.EventMapper.DATE_TIME_FORMATTER;

public class ParticipationMapper {


    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static ParticipationDto toParticipationDto(Participation participation) {
        return ParticipationDto
                .builder()
                .id(participation.getId())
                .created(participation.getCreated().format(DATE_TIME_FORMATTER))
                .event(participation.getEvent().getId())
                .requester(participation.getRequester().getId())
                .status(participation.getStatus())
                .build();
    }

    public static Participation toParticipation(ParticipationDto participationDto) {
        return Participation
                .builder()
                .id(participationDto.getId())
                .created(LocalDateTime.parse(participationDto.getCreated(),
                        DATE_TIME_FORMATTER))
                .status(participationDto.getStatus())
                .build();
    }


//    public static Request toRequest(Event event, User requester) {
//        Request request = new Request();
//        request.setEvent(event);
//        request.setRequester(requester);
//        request.setCreated(LocalDateTime.now());
//        request.setStatus(event.getRequestModeration() ? PENDING : CONFIRMED);
//        return request;
//    }

    public static ParticipationRequestDto toParticipationRequestDto(Participation request) {
        return new ParticipationRequestDto(
                request.getId(),
                toStringDateTime(request.getCreated()),
                request.getEvent().getId(),
                request.getRequester().getId(),
                request.getStatus().toString()
        );
    }

    public static List<ParticipationRequestDto> toParticipationRequestDtoList(List<Participation> requests) {
        return requests.stream().map(ParticipationMapper::toParticipationRequestDto).collect(Collectors.toList());
    }


    public static String toStringDateTime(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }


}
