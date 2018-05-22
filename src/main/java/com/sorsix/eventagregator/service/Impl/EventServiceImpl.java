package com.sorsix.eventagregator.service.Impl;

import com.sorsix.eventagregator.model.DTO.EventDTO;
import com.sorsix.eventagregator.model.Event;
import com.sorsix.eventagregator.model.enums.Type;
import com.sorsix.eventagregator.repository.EventRepository;
import com.sorsix.eventagregator.service.EventService;
import com.sorsix.eventagregator.utils.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EventServiceImpl implements EventService {
    private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

    private EventRepository repository;

    public EventServiceImpl(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Event createEvent(Event event) {
        logger.info("creating a new event");
        return repository.save(event);
    }

    @Override
    public void deleteEvent(Long id) {
        logger.info("Deleting event with id: {}", id);
        repository.deleteById(id);
    }

    @Override
    public List<Event> findEventsForUserWithType(String userId, Type type) {
        logger.info("{} {}", userId, type);
        return repository.findEventsByUserIdAndType(userId, type);
    }

    @Override
    public List<Event> findEventsBetween(String userId, LocalDateTime startTime, LocalDateTime endTime) {
        return repository.findAllByUserIdAndStartTimeBetween(userId, startTime, endTime);
    }

    @Override
    public Optional<Event> updateEvent(Event updatedEvent) {
        return repository.findById(updatedEvent.getId())
                .map(event -> {
                    event = updatedEvent;
                    return repository.save(event);
                });
    }

    @Override
    public List<Event> findEventsForThisWeeK(String userId) {
        LocalDateTime startOfWeek = DateTimeUtils.getFirstDayOfWeek();
        LocalDateTime endOfWeek = DateTimeUtils.getLastDayOfWeek();
        return repository.findAllByUserIdAndStartTimeBetween(userId, startOfWeek, endOfWeek);
    }

    @Override
    public List<EventDTO> findAllPublicEventsAfterNow(String userId) {
        return repository.findAllByUserIdAndTypeAndStartTimeAfter(userId, Type.PUBLIC, LocalDateTime.now())
                .stream()
                .map(EventDTO::createFromEvent)
                .collect(Collectors.toList());
    }
}
