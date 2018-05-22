package com.sorsix.eventagregator.model.DTO;

import com.google.common.base.Objects;
import com.sorsix.eventagregator.model.Event;

import java.time.LocalDateTime;

public class EventDTO {

    public final Long id;
    public final String title;
    public final LocalDateTime startTime;

    public EventDTO(Long id, String title, LocalDateTime startTime) {
        this.id = id;
        this.title = title;
        this.startTime = startTime;
    }

    public static EventDTO createFromEvent(Event event) {
        Long id = event.getId();
        String title = event.getTitle();
        LocalDateTime startTime = event.getStartTime();
        return new EventDTO(id, title, startTime);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("title", title)
                .add("startTime", startTime)
                .toString();
    }
}
