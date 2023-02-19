package com.example.mainserver.participation;

import com.example.mainserver.participation.model.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ParticipationRepository  extends JpaRepository<Participation, Long> {
 //   List<Participation> findAllByRequesterId(Long userId);

    Participation findByEventIdAndRequesterId(Long eventId, Long userId);


    @Query("select p from Participation p  where p.event.id = ?1 and p.status = 'CONFIRMED' " +
            "order by p.created asc")
    List<Participation> getParticipations(Long eventId);


  //  int countParticipationByEventIdAndStatus(Long eventId, StatusRequest status);

 //   List<Participation> findAllByEventIdAndEventInitiatorId(Long eventId, Long userId);

 //   Optional<Participation> findByIdAndRequesterId(Long id, Long userId);
}
