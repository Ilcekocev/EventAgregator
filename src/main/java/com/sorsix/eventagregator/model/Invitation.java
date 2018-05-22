package com.sorsix.eventagregator.model;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Invitation {

    @EmbeddedId
    InvitationId invitationId;
    @ManyToOne
    @JoinColumn(name = "event_id", insertable = false, updatable = false)
    private Event event;
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
    private boolean notified;

    public Invitation() {
    }

    public Invitation(InvitationId invitationId, Event event, User user) {
        this.invitationId = invitationId;
        this.event = event;
        this.user = user;
        this.notified = false;
    }

    public static Invitation createInvitationForRegisteredUser(User user, Event event) {
        InvitationId invitationId = new InvitationId(user.getId(), event.getId());
        return new Invitation(invitationId, event, user);
    }


    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("invitationId", invitationId)
                .add("eventId", event)
                .add("notified", notified)
                .toString();
    }
}
