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
    String id;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Event> events;

    @OneToOne
    UserDetails userDetails;

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
