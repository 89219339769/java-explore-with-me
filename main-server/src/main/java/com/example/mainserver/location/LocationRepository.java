package com.example.mainserver.location;


import com.example.mainserver.location.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LocationRepository extends JpaRepository<Location, Long> {
}