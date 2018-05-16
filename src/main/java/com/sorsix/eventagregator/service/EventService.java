package com.sorsix.eventagregator.service;

import com.sorsix.eventagregator.model.Event;
import com.sorsix.eventagregator.model.enums.Type;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public interface EventService {

    Event createEvent(Event event);

    Optional<Event> updateEvent(Event updatedEvent);

    void deleteEvent(Long id);

    List<Event> findEventsForUserWithType(String userId, Type type);

    List<Event> findEventsBetween(String userId, LocalDateTime startTime, LocalDateTime endTime);

    List<Event> findEventsForThisWeeK(String userId);

}
