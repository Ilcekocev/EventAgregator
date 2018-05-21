package com.sorsix.eventagregator.model;

import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class InvitationId implements Serializable {

    @Column(name = "user_id")
    private String userId;
    @Column(name = "event_id")
    private Long eventId;

    public InvitationId() {
    }

    public InvitationId(String user, Long eventId) {
        this.userId = user;
        this.eventId = eventId;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("userId", userId)
                .add("eventId", eventId)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvitationId that = (InvitationId) o;
        return Objects.equal(userId, that.userId) &&
                Objects.equal(eventId, that.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId, eventId);
    }
}
