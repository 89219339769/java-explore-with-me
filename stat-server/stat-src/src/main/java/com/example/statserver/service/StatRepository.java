package com.example.statserver.service;

import com.example.statserver.model.EndpointHit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface StatRepository extends JpaRepository<EndpointHit, Long> {


    @Query("select e from EndpointHit e  where e.timestamp > ?1 and e.timestamp < ?2 " +

            "order by e.timestamp asc")
    List<EndpointHit> getStat(LocalDateTime start, LocalDateTime end);


}
