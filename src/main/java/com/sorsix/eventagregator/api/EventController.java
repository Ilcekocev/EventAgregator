package com.sorsix.eventagregator.api;

import com.sorsix.eventagregator.model.DTO.EventDTO;
import com.sorsix.eventagregator.model.Event;
import com.sorsix.eventagregator.model.enums.Type;
import com.sorsix.eventagregator.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;
    private final static Logger logger = LoggerFactory.getLogger(EventController.class);

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Event> createNewEvent(@RequestBody Event event) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(eventService.createEvent(event));
    }

    @PatchMapping
    public ResponseEntity<Event> updateEvent(@RequestBody Event event) {
        logger.info("{}", event.getId());
        return this.eventService.updateEvent(event)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/private/{userId}")
    public List<Event> getAllPrivateEvents(@PathVariable String userId) {
        return eventService.findEventsForUserWithType(userId, Type.PRIVATE);
    }

    @GetMapping("/weekly/{userId}")
    public List<Event> getAllEventsForThisWeek(@PathVariable String userId) {
        return eventService.findEventsForThisWeeK(userId);
    }

    @GetMapping("/between")
    public List<Event> getAllEventsBetweenDates(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime,
                                                @RequestParam String userId) {
        logger.info("Start time: {}\nEnd time {}", startTime, endTime);
        return eventService.findEventsBetween(userId, startTime, endTime);
    }

    @GetMapping("/public/{userId}")
    public List<EventDTO> findAllPublicEventsAfterNow(@PathVariable String userId) {
        return eventService.findAllPublicEventsAfterNow(userId);
    }


}
