package com.sorsix.eventagregator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Invitation {

    @Id
    private String email;
    @ManyToOne
    private Event event;
    @ManyToOne
    private User user;
    private boolean notified;

    public Invitation() {
    }

    public Invitation(String email, Event event, User user, boolean notified) {
        this.email = email;
        this.event = event;
        this.user = user;
        this.notified = notified;
    }

    public Invitation(String email, Event event, User user) {
        this.email = email;
        this.event = event;
        this.user = user;
    }

    public Invitation(String email, Event event) {
        this.email = email;
        this.event = event;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("email", email)
                .add("event", event)
                .add("notified", notified)
                .toString();
    }
}
