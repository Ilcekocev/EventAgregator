package com.sorsix.eventagregator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @Column(name = "user_id")
    String id;
    @OneToOne
    UserDetails userDetails;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Event> createdEvents;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Invitation> invitations;

    public User() {
    }

    public User(String email, UserDetails userDetails) {
        this.id = email;
        this.userDetails = userDetails;
    }

    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("email", id)
                .add("userDetails", userDetails)
                .toString();
    }
}
