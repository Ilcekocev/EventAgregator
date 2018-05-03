package com.sorsix.eventagregator.api;

import com.sorsix.eventagregator.model.Event;
import com.sorsix.eventagregator.service.EventService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(eventService.createEvent(event));
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
         eventService.deleteEvent(id);
         return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        return eventService.findEventById(id)
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
