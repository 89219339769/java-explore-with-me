package com.example.mainserver.event;

import com.example.mainserver.category.repository.CategoryRepository;
import com.example.mainserver.event.model.*;
import com.example.mainserver.exceptions.NotFoundException;

import com.example.mainserver.location.model.Location;
import com.example.mainserver.location.LocationRepository;
import com.example.mainserver.user.UserRepository;
import com.example.mainserver.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{


    private final EventRepository eventRepository;
  //  private final ParticipationRepository participationRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;



    @Override
    public EventDto createEvent(Long userId, NewEventDto eventDto) {
    //    User user = checkAndGetUser(userId);

        User user =   userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user with id = " + userId + " not found"));

       Event event = EventMapper.toEvent(eventDto);
//        if (event.getEventDate().isBefore(LocalDateTime.now().plusHours(2))) {
//            throw new BadRequestException("date event is too late");
//        }
        Location location = locationRepository.save(eventDto.getLocation());

        event.setCategory(categoryRepository.findById(eventDto.getCategory())
                .orElseThrow(() -> new NotFoundException("category not found")));


        event.setLocation(location);
        event.setInitiator(user);
        return EventMapper.toEventDto(eventRepository.save(event));
    }



}
