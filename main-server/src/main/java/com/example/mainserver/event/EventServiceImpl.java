package com.example.mainserver.event;

import com.example.mainserver.category.model.Category;
import com.example.mainserver.category.repository.CategoryRepository;
import com.example.mainserver.event.model.*;
import com.example.mainserver.exceptions.EventNotFoundException;

import com.example.mainserver.exceptions.WrongDateException;
import com.example.mainserver.location.model.Location;
import com.example.mainserver.location.LocationRepository;
import com.example.mainserver.user.UserRepository;
import com.example.mainserver.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.example.mainserver.event.model.State.PUBLISHED;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {


    private final EventRepository eventRepository;
    //  private final ParticipationRepository participationRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;


    @Override
    public EventDto createEvent(Long userId, NewEventDto eventDto) {
        //    User user = checkAndGetUser(userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user with id = " + userId + " not found"));

        Event event = EventMapper.toEvent(eventDto);
        if (event.getEventDate().isBefore(LocalDateTime.now())) {
            throw new WrongDateException("Field: eventDate. Error: должно содержать дату, которая еще не наступила. Value:" + (event.getEventDate()));
        }
        Location location = locationRepository.save(eventDto.getLocation());

        event.setCategory(categoryRepository.findById(eventDto.getCategory())
                .orElseThrow(() -> new EventNotFoundException("Field: category. Error: must not be blank. Value: null")));


        event.setLocation(location);
        event.setInitiator(user);
        return EventMapper.toEventDto(eventRepository.save(event));
    }

    @Override
    public EventDto publishEvent(Long eventId, UpdateEventAdminRequest updateEventAdminRequest) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("event with id = " + eventId + " not found"));
        if (event.getEventDate().isBefore(LocalDateTime.now().plusHours(1))) {
            throw new RuntimeException("event must start min after one hour of now");
        }

        if (updateEventAdminRequest.getCategory() != null) {
            Category category = categoryRepository.findById(updateEventAdminRequest.getCategory())
                    .orElseThrow(() -> new RuntimeException("category with id = " + updateEventAdminRequest.getCategory() + " not found"));
            event.setCategory(category);
        }
        if (updateEventAdminRequest.getAnnotation() != null) {
            event.setAnnotation(updateEventAdminRequest.getAnnotation());
        }

        if (updateEventAdminRequest.getDescription() != null) {
            event.setDescription(updateEventAdminRequest.getDescription());
        }
        if (updateEventAdminRequest.getEventDate() != null) {
            LocalDateTime eventDate = LocalDateTime.parse(updateEventAdminRequest.getEventDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            event.setEventDate(eventDate);
        }
        if (updateEventAdminRequest.getLocation() != null) {
            Location location = locationRepository.findById(event.getLocation().getId())
                    .orElseThrow(() -> new RuntimeException("user with id = " + event.getLocation().getId() + " not found"));
            location.setLat(updateEventAdminRequest.getLocation().getLat());
            location.setLon(updateEventAdminRequest.getLocation().getLon());
            event.setLocation(location);
        }
        if (updateEventAdminRequest.getPaid() != null) {
            event.setPaid(updateEventAdminRequest.getPaid());
        }
        if (updateEventAdminRequest.getParticipantLimit() != null) {
            event.setParticipantLimit(updateEventAdminRequest.getParticipantLimit());
        }
        if (updateEventAdminRequest.getRequestModeration() != null) {
            event.setRequestModeration(updateEventAdminRequest.getRequestModeration());
        }
        if (updateEventAdminRequest.getTitle() != null)
            event.setTitle(updateEventAdminRequest.getTitle());

        event.setState(PUBLISHED);
        return EventMapper.toEventDto(eventRepository.save(event));

    }

    @Override
    public EventDto getEvent(Long id) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("event with id = " + id + " not found"));

        Long views = event.getViews();
        event.setViews(views + 1);
        eventRepository.save(event);
        return EventMapper.toEventDto(event);
    }


}
