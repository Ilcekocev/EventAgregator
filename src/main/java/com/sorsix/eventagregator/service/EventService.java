package com.sorsix.eventagregator.service;

import com.sorsix.eventagregator.model.Event;
import com.sorsix.eventagregator.model.Type;
import com.sorsix.eventagregator.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public interface EventService {

    Event createEvent(Event event);

    Event createRepetitiveEvent(Event event);

    void deleteEvent(Long id);

    Optional<Event> findEventById(Long id);

    //This method needs to be in userRepisitiry
//    List<Event> findEventsForUser(User user);

    List<Event> findEventsByDate(LocalDateTime startTime, LocalDateTime endTime);

    List<Event> getAllEvents();

    //This method needs to be in userRepisitory
    //List<Event> findForUserByMonth(Long id, Integer month);

}
