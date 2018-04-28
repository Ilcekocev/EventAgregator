package com.sorsix.eventagregator.service;

import com.sorsix.eventagregator.model.Event;
import com.sorsix.eventagregator.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventService {

    Event createEvent(String title, String type, String description, LocalDateTime startTime,
                      LocalDateTime endTime);

    List<Event> createRepetitiveEvent(String title, String type, String description, LocalDateTime startTime,
                                       LocalDateTime endTime,  String ... days);

    void deleteEvent(Long id);

    Optional<Event> findEventById(Long id);

    List<Event> findEventsForUser(User user);

    List<Event> findForUserByMonth(Long id, Integer month);

}
