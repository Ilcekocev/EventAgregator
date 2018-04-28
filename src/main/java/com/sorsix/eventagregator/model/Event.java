package com.sorsix.eventagregator.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private Type type;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @ManyToOne
    private User user;

    public String getTitle() {
        return title;

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStart() {
        return startTime;
    }

    public void setStart(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEnd() {
        return endTime;
    }

    public void setEnd(LocalDateTime endTime) {
        this.endTime = endTime;
    }

}
