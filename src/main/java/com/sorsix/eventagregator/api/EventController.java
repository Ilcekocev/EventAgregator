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

    private final  EventService eventService;
    private final static Logger logger = LoggerFactory.getLogger(EventController.class);

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        logger.info("Im here !");
        logger.info("Printing event {}", event);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(eventService.createEvent(event));
    }

    @GetMapping
    public List<Event> getAllEvents() {
        logger.info("Im here !");
        return eventService.getAllEvents();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
         eventService.deleteEvent(id);
         return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        return eventService.findEventById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/private/{id}")
    public List<Event> getAllPrivateEvents(@PathVariable String id) {
        return
                eventService.findEventsForUserWithType(id, Type.PRIVATE);
    }

    @PatchMapping
    public ResponseEntity<Event> updateEvent(@RequestBody Event event) {
        logger.info("{}", event.getId());
        return this.eventService.updateEvent(event)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    /**
     *
     *
     * @param start
     * @param end
     * @return
     * TODO:  Probably should be set between start and end ?
     */
    @GetMapping("/eventsOnDate")
    public List<Event> getEventsOnDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
         return eventService.findEventsByDate(start,end);
    }
}
