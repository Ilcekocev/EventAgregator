package com.sorsix.eventagregator.api;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/private/{id}")
    public List<Event> getAllPrivateEvents(@PathVariable String id) {
        return eventService.findEventsForUserWithType(id, Type.PRIVATE);
    }

    @GetMapping("/between")
    public List<Event> getAllEventsBetweenDates(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime,
                                                @RequestParam String id) {
        logger.info("Start time: {}\nEnd time {}", startTime, endTime);
        return eventService.findEventsBetween(id, startTime, endTime);
    }

    @PatchMapping
    public ResponseEntity<Event> updateEvent(@RequestBody Event event) {
        logger.info("{}", event.getId());
        return this.eventService.updateEvent(event)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
