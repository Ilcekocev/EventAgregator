package com.sorsix.eventagregator.service.Impl;

import com.sorsix.eventagregator.model.Event;
import com.sorsix.eventagregator.repository.EventRepository;
import com.sorsix.eventagregator.service.EventService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;

    public EventServiceImpl(EventRepository repository) {
        eventRepository = repository;
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event createRepetitiveEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Optional<Event> findEventById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public List<Event> findEventsByDate(LocalDateTime startTime, LocalDateTime endTime) {
        return eventRepository.findEventByStartTimeAndEndTime(startTime,endTime);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }


}
