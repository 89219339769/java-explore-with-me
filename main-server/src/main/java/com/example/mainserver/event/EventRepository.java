package com.example.mainserver.event;

import com.example.mainserver.event.model.Event;
import com.example.mainserver.participation.model.Participation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {


    @Query("select e from Event e  where e.initiator.id = ?1 " +
            "order by e.createdOn asc")
    List<Event> getEventsByUser(Long userId, Pageable pageable);


    @Query("select e from Event e  where e.initiator.id = ?1 and e.id =?2 " +
            "order by e.createdOn asc")
    Event getEventByUser(Long userId, Long eventId);


    @Query("select e from Event e  where   e.eventDate > ?1 and e.eventDate < ?2 " +
            "order by e.createdOn asc")
    List<Event> getEventsByAdmin(LocalDateTime rangeStart, LocalDateTime rangeEnd, Pageable pageable);

    @Query("select e from Event e  where   e.eventDate > ?3 and e.eventDate < ?4 and upper(e.description) like upper(concat('%', ?1, '%'))" +
            "and e.paid = ?2 " +
            "order by e.createdOn asc")
    List<Event> getEventsPublicSortDate(String text, Boolean paid, LocalDateTime rangeStart,
                                        LocalDateTime rangeEnd, Pageable pageable);

    @Query("select e from Event e  where   e.eventDate > ?3 and e.eventDate < ?4 and upper(e.description) like upper(concat('%', ?1, '%'))" +
            "and e.paid = ?2 " +
            "order by e.views asc")
    List<Event> getEventsPublicSortDateViews(String text, Boolean paid, LocalDateTime rangeStart,
                                             LocalDateTime rangeEnd, Pageable pageable);

    @Query("select e from Event e  where   e.eventDate > ?3 and e.eventDate < ?4 and upper(e.description) like upper(concat('%', ?1, '%'))" +
            "and upper(e.annotation) like upper(concat('%', ?1, '%'))" +
            "and e.paid = ?2 " )
    List<Event> getEventsPublic(String text, Boolean paid, LocalDateTime rangeStart,
                                             LocalDateTime rangeEnd, Pageable pageable);
    @Query("select e from Event e ")
    List<Event> getEventWhithotQuerry(Pageable pageable);

    @Query("select e from Event e where e.category.id =  ?1 ")
    List<Event> findByCategoryId(Long id);
}
