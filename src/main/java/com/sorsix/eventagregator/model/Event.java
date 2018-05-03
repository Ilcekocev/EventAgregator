package com.sorsix.eventagregator.model;


import com.google.common.base.Objects;
import com.sorsix.eventagregator.model.enums.Type;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private Type type;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String externalLink;
    @ManyToOne
    private User user;

    public Event() {
    }

    public Event(String title, Type type, String description, LocalDateTime startTime, LocalDateTime endTime, String externalLink, User user) {
        this.title = title;
        this.type = type;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.externalLink = externalLink;
        this.user = user;
    }

    public Event(String title, Type type, String description, LocalDateTime startTime, LocalDateTime endTime, User user) {
        this.title = title;
        this.type = type;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("title", title)
                .add("type", type)
                .add("description", description)
                .add("startTime", startTime)
                .add("endTime", endTime)
                .add("externalLink", externalLink)
                .add("user", user)
                .toString();
    }
}
