package com.example.mainserver.participation;

import com.example.mainserver.participation.model.Participation;
import com.example.mainserver.participation.model.StatusRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
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

    List<Participation> findAllByEventId(Long eventId);


    int countParticipationByEventIdAndStatus(Long eventId, StatusRequest status);



    @Query(value = "SELECT r FROM Participation r WHERE r.event.id = :eventId AND r.id IN :requestIds")
    List<Participation> findStoredUpdRequests(@Param("eventId") Long eventId, @Param("requestIds") List<Long> ids);

}
