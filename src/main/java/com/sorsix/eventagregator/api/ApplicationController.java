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
public class ApplicationController {

    EventService eventService;

    public ApplicationController(EventService eventService) {
        this.eventService = eventService;
    }


    //Get info about the user
    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

    @PostMapping("/api/createEvent")
    public ResponseEntity<Void> createEvent(@RequestBody Event event) {
        eventService.createEvent(event);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/api/events")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @DeleteMapping("/api/delete/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
         eventService.deleteEvent(id);
         return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/event/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        Event event = eventService.findEventById(id).orElse(null);
        if (event == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(event,HttpStatus.OK);
    }

    @GetMapping("/api/eventsOnDate")
    public List<Event> getEventsOnDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
         return eventService.findEventsByDate(start,end);
    }

}
