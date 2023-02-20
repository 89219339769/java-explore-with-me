package com.example.mainserver.event;

import com.example.mainserver.event.model.Event;
import com.example.mainserver.participation.model.Participation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {


    @Query("select e from Event e  where e.initiator.id = ?1 " +
            "order by e.createdOn asc")
    List<Event> getEventsByUser(Long userId, Pageable pageable);


    @Query("select e from Event e  where e.initiator.id = ?1 and e.id =?2 " +
            "order by e.createdOn asc")
    Event getEventByUser(Long userId, Long eventId);


}
