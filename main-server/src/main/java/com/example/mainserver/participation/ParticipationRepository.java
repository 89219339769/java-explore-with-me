package com.example.mainserver.participation;

import com.example.mainserver.participation.model.Participation;
import com.example.mainserver.participation.model.ParticipationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ParticipationRepository  extends JpaRepository<Participation, Long> {
    List<Participation> findAllByRequesterId(Long userId);

    Participation findByEventIdAndRequesterId(Long eventId, Long userId);


    @Query("select p from Participation p  where p.event.id = ?1 and p.status = 'CONFIRMED' " +
            "order by p.created asc")
    List<Participation> getParticipations(Long eventId);


    @Query("select p from Participation p  where p.event.id = ?2 " +
            "order by p.created asc")
    List<Participation> getParticipationRequestsByInitiator(Long eventId);

    @Query("select p from Participation p  where p.event.id = ?1 and p.requester.id = ?2 " +
            "order by p.created asc")
    Participation getParticipationRequest(Long userId, Long eventId);

    @Query("select p from Participation p  where p.id = ?1 and p.requester.id = ?2 " +
            "order by p.created asc")
    Participation canselParticipationRequest(Long reqId, Long userId);

}
