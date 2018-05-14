package com.sorsix.eventagregator.service.Impl;

import com.sorsix.eventagregator.model.Event;
import com.sorsix.eventagregator.model.enums.Type;
import com.sorsix.eventagregator.repository.EventRepository;
import com.sorsix.eventagregator.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class EventServiceImpl implements EventService {
    private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

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
    public List<Event> findEventsForUserWithType(String userId, Type type) {
        logger.info("{} {}", userId, type);
        return eventRepository.findEventsByUserIdAndType(userId, type);
    }

    @Override
    public List<Event> findEventsByDate(LocalDateTime startTime, LocalDateTime endTime) {
        return eventRepository.findEventByStartTimeAndEndTime(startTime, endTime);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> updateEvent(Event updatedEvent) {
       return eventRepository.findById(updatedEvent.getId())
                .map(event -> {
                    event = updatedEvent;
                    event.setUser(updatedEvent.getUser());
                    logger.info("Event user: {}", updatedEvent.getUser());
                    return eventRepository.save(event);
                });
    }
}
